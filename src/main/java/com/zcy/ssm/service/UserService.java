package com.zcy.ssm.service;

import com.zcy.ssm.base.service.BaseService;
import com.zcy.ssm.entity.User;

import java.util.List;

/**
 * UserService
 * Created by zcy on 2016/9/14.
 */
public interface UserService extends BaseService {

    List<User> getAllUser();

    User getUserById(String userId);

    User getUserByPhone(String userPhone);

    Long insertUser(User user);

}
