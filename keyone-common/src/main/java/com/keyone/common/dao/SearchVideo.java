package com.keyone.common.dao;


import java.util.ArrayList;
import java.util.List;

import com.keyone.common.pojo.Video;
import com.keyone.common.util.SearchUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * <p>Title: SearchVideo</p>
 * <p>Description:根据时间获取相应的视频片段 </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-1-6下午3:05:06
 * @version  1.0
 */

public class SearchVideo
{
	private static  boolean bl = true;

	/**
	* <p>Title: search</p>
	* <p>Description:根据时间获取相应的视频片段方法 </p>
	* @param time
	* @return
	* @throws Exception 
	*/
	public List<Video> search()
	{
		//ArrayList<Video> videoList = new ArrayList<Video>();
		ArrayList<Video> videoListAll =null;

		try
		{
			videoListAll = (ArrayList<Video>) SearchUtil.parseJson();
//			if(bl){
//				Delete delete = new Delete();
//				delete.start();
//				bl=false;
//			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return videoListAll;
	}
}
class Delete extends Thread
{

	public void run()
	{
		DeleteVideo deleteVideo=new DeleteVideo();
		deleteVideo.delete();
	}

}
