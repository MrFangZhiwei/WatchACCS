package com.keyone.service;

import com.keyone.common.pojo.PageVideo;

public interface PageVideoService
{
	PageVideo getPageVideo(int page) throws Exception;
	PageVideo getMobilePageVideo(int page) throws Exception;
}
