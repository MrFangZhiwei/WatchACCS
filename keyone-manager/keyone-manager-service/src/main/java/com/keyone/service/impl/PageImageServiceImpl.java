package com.keyone.service.impl;

import com.keyone.common.pojo.Image;
import com.keyone.common.pojo.KeyoneResult;
import com.keyone.service.PageImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: WatchACCS
 * @Description:
 * @Company:www.keyonecn.com
 * @author:fzw
 * @date:2017/10/31 10:05
 * @version:1.0
 */
@Service
public class PageImageServiceImpl implements PageImageService
{

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    public KeyoneResult getPageImage() throws Exception
    {
        List<Image> imageList = new ArrayList<>();
        File folders = new File(IMAGE_URL);
        File flist[] = folders.listFiles();
        if (flist == null || flist.length == 0)
        {
            return KeyoneResult.build(400, "未发现图片");
        }
        for (File file : flist)
        {
            Image image = new Image();
            image.setFilename(file.getName());
            image.setFilepath(IMAGE_BASE_URL + file.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date(file.lastModified()));
            image.setFiledate(time);
            imageList.add(image);
        }
        return KeyoneResult.build(200, "获取图片数据成功", imageList);
    }
}