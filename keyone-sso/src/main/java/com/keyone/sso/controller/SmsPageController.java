package com.keyone.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SSO系统短信登录页面跳转controller
 * <p>Title: SmsUserController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-4下午11:39:28
 * @version  1.0
 */
@Controller
public class SmsPageController
{
	@RequestMapping("/showSms")
	public String userLogin()
	{
		return "smslogin";
	}

}
