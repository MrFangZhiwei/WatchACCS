package com.keyone.service.impl;

import com.keyone.common.pojo.PageVideo;
import com.keyone.common.pojo.Video;
import com.keyone.common.util.JsonUtils;
import com.keyone.common.util.SearchVideoUtil;
import com.keyone.service.PageVideoService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PageVideoServiceImpl implements PageVideoService
{

	@Override
	public PageVideo getPageVideo(int page) throws Exception
	{
		String json = SearchVideoUtil.parseJson(page);
		//获得ret下面的map
		Map jsonpojo = JsonUtils.jsonToPojo(json, Map.class);
		Object object = jsonpojo.get("ret");
		String objectToJson = JsonUtils.objectToJson(object);
		//总记录数目
		Map jsonpojo1 = JsonUtils.jsonToPojo(objectToJson, Map.class);
		int toalpage = (int)jsonpojo1.get("totalRecords")%5;
		Object object2 = jsonpojo1.get("list");
		String json2 = JsonUtils.objectToJson(object2);
		//获得list对象
		List<Map> jsonToList2 = JsonUtils.jsonToList(json2, Map.class);
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageVideo pageVideo=new PageVideo();
		pageVideo.setPages(toalpage);
		List<Video> videolist = new ArrayList<Video>();
		pageVideo.setVideolist(videolist);
		for (Map m : jsonToList2)
		{
			Video video=new Video();
			video.setVideoName(m.get("videoName").toString());
			video.setPictureUrl(m.get("snapshotUrl").toString());
			video.setVideoUrl(m.get("origUrl").toString());
			video.setDuration(formatDateTime(Long.parseLong(m.get("duration").toString())));
			video.setCreateTime(dateFormat.format(new Date(Long.parseLong((m.get("createTime").toString())))));
			videolist.add(video);
		}
	
		return pageVideo;
	}
	
	@Override
	public PageVideo getMobilePageVideo(int page) throws Exception
	{
		String json = SearchVideoUtil.parseMobileJson(page);
		//获得ret下面的map
		Map jsonpojo = JsonUtils.jsonToPojo(json, Map.class);
		Object object = jsonpojo.get("ret");
		String objectToJson = JsonUtils.objectToJson(object);
		//总记录数目
		Map jsonpojo1 = JsonUtils.jsonToPojo(objectToJson, Map.class);
		int toalpage = (int)jsonpojo1.get("totalRecords")%5;
		Object object2 = jsonpojo1.get("list");
		String json2 = JsonUtils.objectToJson(object2);
		//获得list对象
		List<Map> jsonToList2 = JsonUtils.jsonToList(json2, Map.class);
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageVideo pageVideo=new PageVideo();
		pageVideo.setPages(toalpage);
		List<Video> videolist = new ArrayList<Video>();
		pageVideo.setVideolist(videolist);
		for (Map m : jsonToList2)
		{
			Video video=new Video();
			video.setVideoName(m.get("videoName").toString());
			video.setPictureUrl(m.get("snapshotUrl").toString());
			video.setVideoUrl(m.get("origUrl").toString());
			video.setDuration(formatDateTime(Long.parseLong(m.get("duration").toString())));
			video.setCreateTime(dateFormat.format(new Date(Long.parseLong((m.get("createTime").toString())))));
			videolist.add(video);
		}
	
		return pageVideo;
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
