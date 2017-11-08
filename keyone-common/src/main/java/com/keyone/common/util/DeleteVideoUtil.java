package com.keyone.common.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.pojo.Video;
import com.keyone.common.pojo.VideoRecord;

public class DeleteVideoUtil
{
	public  static KeyoneResult deleteVideo(List<Long> vidlist) throws Exception
	{
		try
		{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = "https://vcloud.163.com/app/vod/video/delete";
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
			StringEntity params = new StringEntity("{\"vids\":" + vidlist + "}", Consts.UTF_8);
			httpPost.setEntity(params);
			// 执行请求
			HttpResponse response;
			response = httpClient.execute(httpPost);
			// 打印执行结果
			System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return KeyoneResult.ok();
	}
}