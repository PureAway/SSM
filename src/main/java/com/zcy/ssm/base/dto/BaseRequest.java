package com.zcy.ssm.base.dto;

import java.io.Serializable;

/**
 * 请求基类
 * Created by zcy on 2016/9/18.
 */
public class BaseRequest implements Serializable {


    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
