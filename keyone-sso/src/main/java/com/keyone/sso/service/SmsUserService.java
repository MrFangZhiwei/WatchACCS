package com.keyone.sso.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keyone.common.pojo.KeyoneResult;

public interface SmsUserService
{
	KeyoneResult getidentifycode(String phone,HttpServletRequest request,HttpServletResponse response);
	KeyoneResult userLogin(String phone,String identifycode,HttpServletRequest request,HttpServletResponse response);
}
