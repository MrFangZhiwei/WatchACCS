package com.keyone.common.util;


import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.keyone.common.pojo.Video;
import com.keyone.common.pojo.VideoRecord;



public class SearchUtil
{
	public static String json = "";

	

	public static List<Video> parseJson() throws Exception
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
		StringEntity params = new StringEntity(
				"{\"currentPage\":1, \"pageSize\":-1, \"status\":0, \"type\":0}", Consts.UTF_8);
		httpPost.setEntity(params);

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		// 打印执行结果
		json = EntityUtils.toString(response.getEntity(), "utf-8");
//		System.out.println(json);
		JSONObject object = JSONObject.fromObject(json);
		JSONObject ret = (JSONObject) object.get("ret");
		JSONArray array = (JSONArray) ret.get("list");
		List<VideoRecord> list = JSONArray.toList(array, new VideoRecord(), new JsonConfig());
		int i=1;
		List<Video> list1=new ArrayList<Video>();
		for (VideoRecord bean : list)
		{
			Video video=new Video();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			DecimalFormat df = new DecimalFormat("0.00");
			String size=df.format(bean.getInitialSize()/1024/1024);
			//System.out.println(ret.getString("pageSize"));
			video.setVid(bean.getVid());
		  // System.out.println("视频名称:"+bean.getVideoName());
			video.setVideoName(bean.getVideoName());
		//	System.out.println("视频"+i+++"链接:"+bean.getOrigUrl());
			video.setVideoUrl(bean.getOrigUrl());
			//System.out.println("创建时间:"+dateFormat.format(new Date(bean.getCreateTime())));
			video.setCreateTime(dateFormat.format(new Date(bean.getCreateTime())));
		//	System.out.println("时长:"+SearchUtil.formatDateTime(bean.getDuration()));
			video.setDuration(SearchUtil.formatDateTime(bean.getDuration()));
			JSONObject object1 = JSONObject.fromObject(GetFirstPicture.getFenmian(bean.getVid()));
			JSONObject ret1 = (JSONObject) object1.get("ret");
			//System.out.println("封面链接:"+ret1.getString("url"));
			video.setPictureUrl(ret1.getString("url"));
			//System.out.println("视频大小:"+size+"MB");
			//System.out.println();
			list1.add(video);
		}
		return list1;
	}
	
	public static String formatDateTime(long mss) {
		  String DateTimes = null;
		  long days = mss / ( 60 * 60 * 24);
		  long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
		  long minutes = (mss %  ( 60 * 60)) /60;
		  long seconds = mss % 60;
		  if(days>0){
		   DateTimes= days + "天" + hours + "小时" + minutes + "分钟"
		     + seconds + "秒"; 
		  }else if(hours>0){
		   DateTimes=hours + "小时" + minutes + "分钟"
		     + seconds + "秒"; 
		  }else if(minutes>0){
		   DateTimes=minutes + "分钟"
		     + seconds + "秒"; 
		  }else{
		   DateTimes=seconds + "秒";
		  }
		  
		  return DateTimes;
		  }
}