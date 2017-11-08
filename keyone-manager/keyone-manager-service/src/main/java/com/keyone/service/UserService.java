package com.keyone.service;

import com.keyone.pojo.KoUser;

public interface UserService
{
	KoUser getUserByToken(String token);
}
