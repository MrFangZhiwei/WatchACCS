package com.keyone.common.util;



import java.util.Random;

public class Config
{
	public static String AppKey = "1681af51443f43ce9c13b8e8080dfa1c";
	public static String AppSecret = "0c79df62c92a458e8709ac1d8699b2cf";
	public static String Nonce = getRandomString(100);
	public static String CurTime = String.valueOf(System.currentTimeMillis());
	public static String CheckSum = CheckSumBuilder.getCheckSum(AppKey, Nonce, CurTime);
	public static String getRandomString(int length)
	{ // length 字符串长度 
		StringBuffer buffer = new StringBuffer("0123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++)
		{
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	public static void main(String[] args)
	{
		System.out.println(CurTime);
		System.out.println(CheckSum);
	}

}
