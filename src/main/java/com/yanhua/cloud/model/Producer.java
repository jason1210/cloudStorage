package com.yanhua.cloud.model;

import java.util.Date;

public class Producer {
    private Integer id;

    private String account;

    private String password;

    private String openIdProducer;

    private String accessToken;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOpenIdProducer() {
        return openIdProducer;
    }

    public void setOpenIdProducer(String openIdProducer) {
        this.openIdProducer = openIdProducer == null ? null : openIdProducer.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}