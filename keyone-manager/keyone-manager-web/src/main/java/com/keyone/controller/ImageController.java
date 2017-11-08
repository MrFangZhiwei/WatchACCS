package com.keyone.controller;

import com.keyone.common.pojo.KeyoneResult;
import com.keyone.service.PageImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController
{

    @Autowired
    private PageImageService pageImageService;

    /**
     * pc端请求图片的ajax请求
     * <p>Title: showImage</p>
     * <p>Description: </p>
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/image")
    @ResponseBody
    public KeyoneResult showImage() throws Exception
    {
        KeyoneResult result = pageImageService.getPageImage();
        return result;

    }

}
