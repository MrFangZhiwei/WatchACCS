package com.keyone.controller;

import com.keyone.common.pojo.PageVideo;
import com.keyone.service.PageVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController
{

    @Autowired
    private PageVideoService pageVideoService;


    /**
     * 页面展示视频的页面
     * <p>Title: showVideoinde</p>
     * <p>Description: </p>
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/video")
    public String showVideoindex() throws Exception
    {
        return "video";
    }


    /**
     * pc端的ajax请求
     * <p>Title: showVideo</p>
     * <p>Description: </p>
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/videolist")
    @ResponseBody
    public PageVideo showVideo(int page) throws Exception
    {
        PageVideo videoList = pageVideoService.getPageVideo(page);
        return videoList;
    }

    /**
     * 手机的ajax请求
     * <p>Title: showMobileVideo</p>
     * <p>Description: </p>
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/mobilevideolist")
    @ResponseBody
    public PageVideo showMobileVideo(int page) throws Exception
    {
        PageVideo videoList = pageVideoService.getMobilePageVideo(page);
        return videoList;
    }


}
