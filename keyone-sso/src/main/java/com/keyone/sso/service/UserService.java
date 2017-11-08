package com.keyone.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.pojo.KoUser;

public interface UserService
{
	KeyoneResult checkData(String content,Integer type);
	KeyoneResult createUser(KoUser user);
	KeyoneResult userLogin(String username,String password,HttpServletRequest request,HttpServletResponse response);
	KeyoneResult getUserByToken(String token);
}
