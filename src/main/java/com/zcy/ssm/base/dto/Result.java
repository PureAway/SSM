package com.zcy.ssm.base.dto;

import java.io.Serializable;

/**
 * 结果集封装
 * Created by zcy on 2016/9/14.
 */
public class Result<T> implements Serializable {

    private T data;

    private String returnMessage;

    private int returnCode;

    public Result(T data, String returnMessage, int returnCode) {
        this.data = data;
        this.returnMessage = returnMessage;
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "Result : [" +
                "data : " + data +
                ", returnMessage : '" + returnMessage +
                ", returnCode : " + returnCode +
                ']';
    }
}
