package com.zcy.ssm.service.serviceImpl;

import com.zcy.ssm.dao.UserDao;
import com.zcy.ssm.entity.User;
import com.zcy.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 户操作实现类
 * Created by zcy on 2016/9/14.
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;


    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public User getUserById(String userId) {
        return userDao.selectUserById(userId);
    }

    public User getUserByPhone(String userPhone) {
        return userDao.selectUserByPhone(userPhone);
    }

    public User getUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }

    public Long insertUser(User user) {
        return userDao.insertUser(user);
    }
}
