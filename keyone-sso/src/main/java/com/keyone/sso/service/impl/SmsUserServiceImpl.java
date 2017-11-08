package com.keyone.sso.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.util.CookieUtils;
import com.keyone.common.util.SmsUtil;
import com.keyone.mapper.KoUserMapper;
import com.keyone.pojo.KoUser;
import com.keyone.pojo.KoUserExample;
import com.keyone.pojo.KoUserExample.Criteria;
import com.keyone.sso.dao.JedisClient;
import com.keyone.sso.service.SmsUserService;

/**
 * 用户验证码登录处理类
 * <p>Title: SmsUserServiceImpl</p>
 * <p>Description:接受手机号码 以及验证码 </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-4-14上午9:29:57
 * @version  1.0
 */
@Service
public class SmsUserServiceImpl implements SmsUserService
{
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private KoUserMapper userMapper;
	//加载Resourse.properties文件
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${SSO_SMS_SESSION_EXPIRE}")
	private Integer SSO_SMS_SESSION_EXPIRE;

	/**
	 * 用户输入验证码 写入Redis
	 * 接受手机号码 以及验证码
	 * <p>Title: getidentifycode</p>
	 * <p>Description: </p>
	 * @param phone
	 * @param identifycode
	 * @return
	 */
	@Override
	public KeyoneResult getidentifycode(String phone, HttpServletRequest request, HttpServletResponse response)
	{
		//-----进行数据库查询比对有没有该手机号码  无则return回去--------
		KoUserExample example = new KoUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<KoUser> list = userMapper.selectByExample(example);
		if (list.size() == 0 || list == null)
		{
			return KeyoneResult.build(400, "您没有权限");

		} else
		{
			//获取得到用户信息 
			KoUser user = list.get(0);
		}
		//调用短信工具类发送返回验证码
		SmsUtil smsUtil = new SmsUtil();
		KeyoneResult result = smsUtil.smsSend(phone);
		if (result.getStatus() == 200)
		{
			//生成用户token	
			String token = UUID.randomUUID().toString();
			String code = result.getMsg();
			//将验证码存入redis中缓存15706844570
			jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, code);
			//设置redis的过期时间为60秒
			jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SMS_SESSION_EXPIRE);
			//添加写cookie的逻辑,cookie的有效期是关闭浏览器就失效
			CookieUtils.setCookie(request, response, "SMS_TOKEN", token);
			//System.out.println("设置cookie成功");
			//返回token
			return KeyoneResult.ok(token);
		} else
		{
			
			return result;
		}

	}

	/**
	 * 用户登录验证短信验证码
	 * <p>Title: userLogin</p>
	 * <p>Description: </p>
	 * @param phone
	 * @param identifycode
	 * @return
	 */
	public KeyoneResult userLogin(String phone, String identifycode, HttpServletRequest request,
			HttpServletResponse response)
	{
		//从cookie中去token
		String token = CookieUtils.getCookieValue(request, "SMS_TOKEN");

		//-----进行redis数据库比对  无则return回去--------
		//根据tokenredis中获取用户信息
		String code = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		//判断json是否空
		if (StringUtils.isBlank(code))
		{
			return KeyoneResult.build(400, "此验证码已经过期,器重新登录");
		} else
		{
			if (identifycode.equals(code))
			{
				//设置验证码过期
				jedisClient.expire(SSO_SMS_SESSION_EXPIRE + ":" + token, -1);
				return KeyoneResult.ok();
			} else
			{
				return KeyoneResult.build(400, "验证码错误");
			}

		}

	}

}
