package com.zcy.ssm.entity;

import com.zcy.ssm.base.dto.BaseRequest;

import java.util.Date;

/**
 * 用户信息封装类
 * Created by zcy on 2016/9/14.
 */
public class User extends BaseRequest {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户电话号码
     *
     *
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户信息最后修改时间
     */
    private Date modifyTime;

    /**
     * 用户信息是否删除
     */
    private int isDelete;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}

