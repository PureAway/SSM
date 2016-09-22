package com.zcy.ssm.service.serviceImpl;

import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.dao.UserDao;
import com.zcy.ssm.entity.User;
import com.zcy.ssm.service.UserService;
import com.zcy.ssm.utils.MD5Util;
import com.zcy.ssm.utils.TextUtils;
import com.zcy.ssm.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 户操作实现类
 * Created by zcy on 2016/9/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;


    public void userRegister(User user, Result result) {
        try {
            if (TextUtils.isEmpty(user.getUserName())) {
                result.setResult(null, "用户名不能为空", 0);
            } else if (TextUtils.isEmpty(user.getPassword())) {
                result.setResult(null, "密码不能为空", 0);
            } else if (TextUtils.isEmpty(user.getUserPhone())) {
                result.setResult(null, "注册手机号不能为空", 0);
            }
            User thisUser = userDao.selectUserByPhone(user.getUserPhone());
            if (null != thisUser) {
                result.setResult(null, "该手机号已经被注册", 0);
            } else {
                user.setCreateTime(new Date());
                user.setModifyTime(new Date());
                Long code = userDao.insertUser(user);
                if (code >= 1) { // 注册成功
                    user.setToken(UUIDUtil.getToken(String.valueOf(code)));
                    result.setResult(user, "注册成功", 1);
                } else { // 注册失败
                    result.setResult(null, "内部服务器错误", 0);
                }
            }
        } catch (Exception e) {
            System.out.println("==用户注册=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }
    }

    public void userLoginByUserName(User user, Result result) {
        try {
            User thisUser = userDao.selectUserByUserName(user.getUserName());
            if (null == thisUser) {
                result.setResult(null, "用户名不存在", 0);
            } else {
                if (MD5Util.MD5(thisUser.getPassword()).equals(user.getPassword()) && thisUser.getUserName().equals(user.getUserName())) {
                    thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                    result.setResult(thisUser, "登录成功", 1);
                } else {
                    result.setResult(null, "密码错误", 0);
                }
            }
        } catch (Exception e) {
            System.out.println("==用户根据用户名登录=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }
    }

    public void userLoginByUserPhone(User user, Result result) {

        try {
            User thisUser = userDao.selectUserByPhone(user.getUserPhone());
            if (null == thisUser) {
                result.setResult(null, "手机号不存在", 0);
            } else {
                if (MD5Util.MD5(thisUser.getPassword()).equals(user.getPassword()) && thisUser.getUserPhone().equals(user.getUserPhone())) {
                    thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                    result.setResult(thisUser, "登录成功", 1);
                } else {
                    result.setResult(null, "密码错误", 0);
                }
            }
        } catch (Exception e) {
            System.out.println("==用户根据手机号登录=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }

    }

    public void reSetPassword(User user, Result result) {

    }

    public void updatePassword(User user, Result result) {

    }
}
