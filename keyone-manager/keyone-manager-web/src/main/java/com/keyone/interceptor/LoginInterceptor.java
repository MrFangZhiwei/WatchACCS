package com.keyone.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.keyone.common.util.CookieUtils;
import com.keyone.pojo.KoUser;
import com.keyone.service.UserService;

public class LoginInterceptor implements HandlerInterceptor
{

	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		//返回ModelView之后
		//反应用户之后
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception
	{
		//handler执行之后,返回ModelView之前

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception
	{
		//handler执行之前
				//返回值绝对handler是否执行。ture执行 false不执行
				//判断用户是否登录
				//从cookie中去token
				String token = CookieUtils.getCookieValue(request, "SMS_TOKEN");
				//根据用户信息,调用sso系统的接口
				KoUser user = userService.getUserByToken(token);
				//取不到用户信息
				if (user == null)
				{
					//跳转到登陆界面，把请求的url作为参数传递给登录界面
					response.sendRedirect("/sso/showSms?redirect=/manager/control/main");
					//response.sendRedirect("http://www.keyonecn.com:8081/showSms?redirect=/control/passage/chooseId");
					//返回false
					return false;
				}
					//取出用户信息
					//放行true
				return true;
	}

}
