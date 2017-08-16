package com.yanhua.cloud.result;

/**
 * Created by zhusc on 2017/8/16.
 * 云盘文件响应实体类
 */
public class FileModel {
    /**
     * 文件id
     */
    private String id;
    /**
     * 该云盘文件所属用户的openId
     */
    private String openId;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件下载播放地址
     */
    private String fileDownloadUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "id='" + id + '\'' +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", fileDownloadUrl='" + fileDownloadUrl + '\'' +
                '}';
    }
}
