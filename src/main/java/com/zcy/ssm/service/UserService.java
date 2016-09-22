package com.zcy.ssm.service;

import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.base.service.BaseService;
import com.zcy.ssm.entity.User;

/**
 * UserService
 * Created by zcy on 2016/9/14.
 */
public interface UserService extends BaseService {

    void userRegister(User user, Result result);

    void userLoginByUserName(User user, Result result);

    void userLoginByUserPhone(User user, Result result);

    void reSetPassword(User user, Result result);

    void updatePassword(User user, Result result);
}
