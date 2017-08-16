package com.yanhua.cloud.model;

import java.util.Date;

public class UserCollect {
    private Integer id;

    private String openIdCollector;

    private String openIdProducer;

    private String fileId;

    private Date createTime;

    private Date updateTime;

    private Boolean flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenIdCollector() {
        return openIdCollector;
    }

    public void setOpenIdCollector(String openIdCollector) {
        this.openIdCollector = openIdCollector == null ? null : openIdCollector.trim();
    }

    public String getOpenIdProducer() {
        return openIdProducer;
    }

    public void setOpenIdProducer(String openIdProducer) {
        this.openIdProducer = openIdProducer == null ? null : openIdProducer.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}