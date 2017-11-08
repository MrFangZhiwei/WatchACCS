package com.keyone.common.pojo;

/**
 * @Title: WatchACCS
 * @Description:用于展示图片的相关信息
 * @Company:www.keyonecn.com
 * @author:fzw
 * @date:2017/10/31 9:39
 * @version:1.0
 */
public class Image
{
    private String filepath;

    public String getFilepath()
    {
        return filepath;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    private String filename;
    private String filedate;

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getFiledate()
    {
        return filedate;
    }

    public void setFiledate(String filedate)
    {
        this.filedate = filedate;
    }
}