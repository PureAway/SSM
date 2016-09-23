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
                user.setCreateTime(System.currentTimeMillis());
                user.setModifyTime(System.currentTimeMillis());
                userDao.insertUser(user);
                user = userDao.selectUserByPhone(user.getUserPhone());
                user.setToken(UUIDUtil.getToken(user.getUserId()));
                result.setResult(user, "注册成功", 1);
            }
        } catch (Exception e) {
            System.out.println("==用户注册=" + e.toString());
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
        try {
            User thisUser = userDao.selectUserByPhone(user.getUserPhone());
            if (null == thisUser) {
                result.setResult(null, "该用户不存在", 0);
                return;
            }
            if (!thisUser.getUserName().equals(user.getUserName())) {
                result.setResult(null, "用户名与注册手机号不匹配", 0);
                return;
            }
            if (thisUser.getUserPhone().equals(user.getUserPhone())) {
                user.setUserId(thisUser.getUserId());
                user.setModifyTime(System.currentTimeMillis());
                userDao.updatePassword(user);
                thisUser.setPassword(user.getPassword());
                thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                result.setResult(thisUser, "密码重设成功", 1);
            }
        } catch (Exception e) {
            System.out.println("==用户根据用户名和手机号重设密码=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }
    }

    public void updatePassword(User user, Result result) {
        try {
            String thisToken = UUIDUtil.getToken(user.getUserId());
            if (!user.getToken().equals(thisToken)) {
                result.setResult(null, "token验证失败", -1);
                return;
            }
            User thisUser = userDao.selectUserById(user.getUserId());
            if (null == thisUser) {
                result.setResult(null, "该用户不存在", 0);
                return;
            }
            if (!thisUser.getPassword().equals(user.getPassword())) {
                result.setResult(null, "旧密码错误", 0);
                return;
            }
            if (thisUser.getPassword().equals(user.getPassword())) {
                user.setPassword(user.getNewPassword());
                user.setModifyTime(System.currentTimeMillis());
                userDao.updatePassword(user);
                thisUser.setPassword(user.getNewPassword());
                thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                result.setResult(thisUser, "重设密码成功", 1);
            }
        } catch (Exception e) {
            System.out.println("==用户修改密码=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }
    }

    public void updateUserInfo(User user, Result result) {
        try {
            String thisToken = UUIDUtil.getToken(user.getUserId());
            if (!user.getToken().equals(thisToken)) {
                result.setResult(null, "token验证失败", -1);
                return;
            }
            User thisUser = userDao.selectUserById(user.getUserId());
            if (null == thisUser) {
                result.setResult(null, "该用户不存在", 0);
                return;
            }
            thisUser = userDao.selectUserByPhone(user.getUserPhone());
            if (null != thisUser) {
                result.setResult(null, "该手机号已被占用", 0);
                return;
            }
            user.setModifyTime(System.currentTimeMillis());
            userDao.updateUser(user);
            thisUser = userDao.selectUserById(user.getUserId());
            thisUser.setToken(UUIDUtil.getToken(user.getUserId()));
            result.setResult(thisUser, "个人信息修改成功", 1);
        } catch (Exception e) {
            System.out.println("==用户修改密码=" + e.toString());
            result.setReturnMessage("内部服务器错误");
            result.setReturnCode(0);
        }
    }
}
