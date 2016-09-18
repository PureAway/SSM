package com.zcy.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.zcy.ssm.base.controller.BaseController;
import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.entity.User;
import com.zcy.ssm.service.UserService;
import com.zcy.ssm.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * UserController
 * Created by zcy on 2016/9/14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register.do", method = {RequestMethod.POST, RequestMethod.GET})
    private Result<User> userRegister(User user) {
        log.info("用户注册");
        log.info("用户注册信息==========" + JSON.toJSONString(user));
        User thisUser = userService.getUserByPhone(user.getUserPhone());
        Result<User> result;
        if (null != thisUser) {
            result = new Result<User>(null, "该手机号已被注册", 0);
        } else {
            user.setUserId("userId");
            user.setCreateTime(new Date());
            user.setModifyTime(new Date());
            Long code = userService.insertUser(user);
            if (code >= 1) { // 注册成功
                user.setToken(UUIDUtil.getToken(String.valueOf(code)));
                result = new Result<User>(user, "注册成功", 1);
            } else { // 注册失败
                result = new Result<User>(null, "注册失败", 0);
            }
        }
        log.info("用户注册结果==========" + JSON.toJSONString(result));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    private Result<User> userLogin(User user) {

        log.info("用户登录");
        log.info("用户登录信息==========" + JSON.toJSONString(user));
        User thisUser = userService.getUserByUserName(user.getUserName());
        Result<User> result;
        if (null == thisUser) {
            result = new Result<User>(null, "用户名不存在", 0);
        } else {
            if (thisUser.getPassword().equals(user.getPassword()) && thisUser.getUserName().equals(user.getUserName())) {
                thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                result = new Result<User>(thisUser, "登录成功", 1);
            } else {
                result = new Result<User>(null, "密码错误", 0);
            }
        }
        return result;

    }


}
