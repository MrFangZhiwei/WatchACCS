package com.keyone.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.util.DeleteVideoUtil;
import com.keyone.common.util.JsonUtils;
import com.keyone.common.util.SearchVideoUtil;

@Service
public class Job1
{
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(Job1.class);

	/**
	 * 每隔一周删除全部视频素材
	 * <p>Title: deleteVideo</p>
	 * <p>Description: </p>
	 * @return
	 * @throws Exception 
	 */
	public KeyoneResult deleteVideo() throws Exception
	{
		String json = SearchVideoUtil.parseJson();
		List<Long> vidlist = new ArrayList<Long>();
		//获取视频集合的vid 加入到vidlist
		//获得ret下面的map
		Map jsonpojo = JsonUtils.jsonToPojo(json, Map.class);
		Object object = jsonpojo.get("ret");
		String objectToJson = JsonUtils.objectToJson(object);
		//获得list下面的map
		Map jsonpojo1 = JsonUtils.jsonToPojo(objectToJson, Map.class);
		Object object2 = jsonpojo1.get("list");
		String json2 = JsonUtils.objectToJson(object2);
		//获得list对象
		List<Map> jsonToList2 = JsonUtils.jsonToList(json2, Map.class);
		for (Map m : jsonToList2)
		{
			long vid = Long.parseLong(m.get("vid").toString());
			vidlist.add(vid);
		}
		//根据vidlist进行批量删除
		DeleteVideoUtil.deleteVideo(vidlist);
		return KeyoneResult.ok();
	}
}
