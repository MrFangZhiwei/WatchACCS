package com.keyone.common.pojo;


public class Video
{
	private String videoName;
	private String createTime;
	private String duration;
	private String pictureUrl;
	private String videoUrl;
	private long vid;

	public long getVid()
	{
		return vid;
	}

	public void setVid(long vid)
	{
		this.vid = vid;
	}

	public String getVideoName()
	{
		return videoName;
	}

	public void setVideoName(String videoName)
	{
		this.videoName = videoName;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public String getPictureUrl()
	{
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl)
	{
		this.pictureUrl = pictureUrl;
	}

	public String getVideoUrl()
	{
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl)
	{
		this.videoUrl = videoUrl;
	}

}
