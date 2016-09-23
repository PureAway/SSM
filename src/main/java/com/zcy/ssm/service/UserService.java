package com.zcy.ssm.service;

import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.base.service.BaseService;
import com.zcy.ssm.entity.User;

/**
 * UserService
 * Created by zcy on 2016/9/14.
 */
public interface UserService extends BaseService {

    /**
     * 用户注册
     *
     * @param user   注册用户
     * @param result 注册结果
     */
    void userRegister(User user, Result result);


    /**
     * 用户登录
     *
     * @param user   登录用户
     * @param result 登录结果
     */
    void userLoginByUserPhone(User user, Result result);

    /**
     * 用户重置密码
     *
     * @param user   用户信息
     * @param result 重置密码结果
     */
    void reSetPassword(User user, Result result);

    /**
     * 用户修改密码
     *
     * @param user   用户信息
     * @param result 用户修改密码结果
     * @param result 用户修改密码结果
     */
    void updatePassword(User user, Result result);

    /**
     * 用户修改个人信息
     *
     * @param user   用户信息
     * @param result 修改结果
     */
    void updateUserInfo(User user, Result result);
}
