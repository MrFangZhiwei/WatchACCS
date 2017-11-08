package com.keyone.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.util.HttpClientUtil;
import com.keyone.pojo.KoUser;
import com.keyone.service.UserService;

/**
 * 用户管理Service
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-5上午9:07:43
 * @version  1.0
 */

@Service
public class UserServiceImpl implements UserService
{
	@Value("${SMS_BASE_URL}")
	public String SMS_BASE_URL;
	@Value("${SMS_USER_TOKEN}")
	private String SMS_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	public KoUser getUserByToken(String token)
	{
		try
		{
			//调用sso系统的服务,根据token取用户信息
			String json = HttpClientUtil.doGet(SMS_BASE_URL + SMS_USER_TOKEN + token);
			if (StringUtils.isBlank(json))
			{
				return null;
			}
			//把json转换成KeyoneResult
			KeyoneResult result = KeyoneResult.formatToPojo(json, KoUser.class);

			if (result.getStatus() == 200)
			{
				KoUser user = (KoUser) result.getData();
				return user;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
