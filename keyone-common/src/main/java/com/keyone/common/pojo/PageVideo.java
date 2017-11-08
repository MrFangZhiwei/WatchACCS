package com.keyone.common.pojo;

import java.util.List;

public class PageVideo
{
	private int pages;
	private List<Video> videolist;
	public int getPages()
	{
		return pages;
	}
	public void setPages(int pages)
	{
		this.pages = pages;
	}
	public List<Video> getVideolist()
	{
		return videolist;
	}
	public void setVideolist(List<Video> videolist)
	{
		this.videolist = videolist;
	}
	
}
