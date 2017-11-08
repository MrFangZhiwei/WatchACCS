package com.keyone.common.dao;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.keyone.common.pojo.Video;
import com.keyone.common.util.CheckSumBuilder;
import com.keyone.common.util.Config;
import com.keyone.common.util.SearchUtil;


public class DeleteVideo
{

	/**
	 * <p>Title: main</p>
	 * <p>Description: </p>
	 * @param args
	*/
	public  void delete()
	{
		List<Long> vidlist = new ArrayList<Long>();
		ArrayList<Video> videoListAll;
		ArrayList<Video> videoList = new ArrayList<Video>();
		try
		{
			Timer timer = new Timer();
			videoListAll = (ArrayList<Video>) SearchUtil.parseJson();
			for (int i = 0; i < videoListAll.size(); i++)
			{
				for (int j = -5; j < -10; j--)
				{
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, j); //得到前一天
					Date d = calendar.getTime();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					//获取6天前的时间
					String a = df.format(d);

					if ((videoListAll.get(i).getCreateTime()).contains(a))
					{
						videoList.add(videoListAll.get(i));
					}
				}
			}
			for (Video v : videoList)
			{
				vidlist.add(v.getVid());
			}
			TaskDemo td = new TaskDemo();
			td.setVidlist(vidlist);
			while (true)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 1); //得到前一天
				Date d = calendar.getTime();
				timer.schedule(new TaskDemo(), d);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}

class TaskDemo extends TimerTask
{
	private List<Long> vidlist = new ArrayList<Long>();

	public List<Long> getVidlist()
	{
		return vidlist;
	}

	public void setVidlist(List<Long> vidlist)
	{
		this.vidlist = vidlist;
	}

	public void run()
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
	}

}
