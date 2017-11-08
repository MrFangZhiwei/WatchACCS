package com.keyone.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.util.CookieUtils;
import com.keyone.common.util.ExceptionUtil;
import com.keyone.pojo.KoUser;
import com.keyone.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService userService;

	@SuppressWarnings(
	{ "unused", "static-access" })
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback)
	{
		KeyoneResult result = null;
		//参数有效性校验
		if (StringUtils.isBlank(param))
		{
			return result = KeyoneResult.build(400, "校验内容不能为空");
		}
		if (type == null)
		{
			return result = KeyoneResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3)
		{
			return result = KeyoneResult.build(400, "校验内容类型未知错误");
		}
		//校验出错并且判断callback是否为空做出不同操作
		if (null != result)
		{
			if (null != callback)
			{
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;

			} else
			{
				return result;
			}
		}
		//到这里说明校验没有出错开始调用服务生成jsonp格式的并且判断callback是否为空做出不同操作
		try
		{
			result = userService.checkData(param, type);
		} catch (Exception e)
		{
			return result.build(500, ExceptionUtil.getStackTrace(e));
		}
		if (null != callback)
		{
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;

		} else
		{
			return result;
		}

	}

	/**
	 * 创建用户
	 * <p>Title: createUser</p>
	 * <p>Description: </p>
	 * @return
	 */
	//加入method=RequestMethod.POST,不然get,post都支持
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public KeyoneResult createUser(KoUser user)
	{
		try
		{
			KeyoneResult result = userService.createUser(user);
			return result;
		} catch (Exception e)
		{
			return KeyoneResult.build(500, ExceptionUtil.getStackTrace(e));
		}

	}

	/**
	 * 用户登录
	 * <p>Title: userLogin</p>
	 * <p>Description: </p>
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public KeyoneResult userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			KeyoneResult result = userService.userLogin(username, password,request,response);
			return result;
		} catch (Exception e)
		{
			return KeyoneResult.build(500, ExceptionUtil.getStackTrace(e));
		}

	}

	/**
	 * 获取用户信息
	 * <p>Title: getUserByToken</p>
	 * <p>Description: </p>
	 * @param token
	 * @return
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback)
	{
		KeyoneResult result = null;
		try
		{
			result = userService.getUserByToken(token);
		} catch (Exception e)
		{
			result = KeyoneResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断是否为jsonp调用 
		if (StringUtils.isBlank(callback))
		{
			return result;
		} else
		{
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
}
