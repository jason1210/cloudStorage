package com.yanhua.cloud.utils;

/**
 * Created by zhusc on 2017/8/15.
 * 加载天翼云属性类
 */
public class ECloudUtils {
    private String appId;

    private String appSecret;

    private String redirectUri;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
