package com.keyone.sso.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keyone.common.pojo.KeyoneResult;

public interface PassageService
{
	KeyoneResult chooseId(String cellId,HttpServletRequest request,HttpServletResponse response) throws Exception;
	KeyoneResult chooseMode(String mode,HttpServletRequest request,HttpServletResponse response) throws Exception;
}
