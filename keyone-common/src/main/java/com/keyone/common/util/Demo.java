package com.keyone.common.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.keyone.common.pojo.Video;
import com.keyone.common.pojo.VideoRecord;

public class Demo
{
	public static String json = "";

	public static String parseJson() throws Exception
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://vcloud.163.com/app/vod/video/list";
		HttpPost httpPost = new HttpPost(url);

		String appKey = Config.AppKey;
		String appSecret = Config.AppSecret;
		String nonce = Config.Nonce;
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

		// 设置请求的参数
		StringEntity params = new StringEntity("{\"currentPage\":1, \"pageSize\":-1, \"status\":0, \"type\":0}",
				Consts.UTF_8);
		httpPost.setEntity(params);

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		// 打印执行结果
		json = EntityUtils.toString(response.getEntity(), "utf-8");
		
		
		return json;
	}

	public static String formatDateTime(long mss)
	{
		String DateTimes = null;
		long days = mss / (60 * 60 * 24);
		long hours = (mss % (60 * 60 * 24)) / (60 * 60);
		long minutes = (mss % (60 * 60)) / 60;
		long seconds = mss % 60;
		if (days > 0)
		{
			DateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
		} else if (hours > 0)
		{
			DateTimes = hours + "小时" + minutes + "分钟" + seconds + "秒";
		} else if (minutes > 0)
		{
			DateTimes = minutes + "分钟" + seconds + "秒";
		} else
		{
			DateTimes = seconds + "秒";
		}

		return DateTimes;
	}
}