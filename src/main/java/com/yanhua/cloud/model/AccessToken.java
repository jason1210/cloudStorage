package com.yanhua.cloud.model;

/**
 * Created by zhusc on 2017/8/10.
 */
public class AccessToken {
    /**
     * 获取到的AT访问令牌
     */
    private String accessToken;
    /**
     * AT访问令牌的有效期（以秒为单位）
     */
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
