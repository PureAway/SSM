package com.zcy.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.zcy.ssm.base.controller.BaseController;
import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.entity.User;
import com.zcy.ssm.service.UserService;
import com.zcy.ssm.utils.MD5Util;
import com.zcy.ssm.utils.TextUtils;
import com.zcy.ssm.utils.UUIDUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * UserController
 * Created by zcy on 2016/9/14.
 */
@Api(value = "/user", description = "用户基本API")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * @param userName  注册用户名
     * @param password  注册密码（明文）
     * @param userPhone 注册手机号
     * @param userEmail 注册邮箱
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))

    @ApiOperation(value = "用户注册", notes = "新用户注册", response = Result.class, httpMethod = "POST", position = 0)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result userRegister(
            @ApiParam(value = "注册用户名", required = true, name = "userName", example = "userName = zcy")
            @RequestParam("userName")
                    String userName,
            @ApiParam(value = "注册密码(明文)", required = true, name = "password", example = "password = 123")
            @RequestParam("password")
                    String password,
            @ApiParam(value = "注册手机号", required = true, name = "userPhone", example = "userPhone = 18888888888")
            @RequestParam("userPhone")
                    String userPhone,
            @ApiParam(value = "注册邮箱", required = false, name = "userEmail", example = "userEmail = zhuchunyao164488421@hotmail.com")
            @RequestParam("userEmail")
                    String userEmail
    ) {
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        Result result = new Result();
        log.info("用户注册");
        log.info("用户注册信息==========" + JSON.toJSONString(user));
        try {
            userService.userRegister(user, result);
        } catch (Exception e) {
            errorHandler(e, result);
        } finally {
            log.info("用户注册结果==========" + JSON.toJSONString(result));
        }
        return result;
    }


    /**
     * 用户根据用户名密码登录
     *
     * @param userName 登录用户名
     * @param password 登录密码(MD5 32小写加密)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginByUserName.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))

    @ApiOperation(value = "用户登录", notes = "用户名登录", response = Result.class, httpMethod = "POST", position = 1)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result userLoginByUserName(
            @ApiParam(value = "登录用户名", required = true, name = "userName", example = "userName = zcy")
            @RequestParam("userName")
                    String userName,
            @ApiParam(value = "登录密码(MD5 32小写加密)", required = true, name = "password", example = "password = e10adc3949ba59abbe56e057f20f883e")
            @RequestParam("password")
                    String password
    ) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        log.info("用户登录");
        log.info("用户登录信息==========" + JSON.toJSONString(user));
        Result result = new Result();
        try {
            userService.userLoginByUserName(user, result);
        } catch (Exception e) {
            errorHandler(e, result);
        } finally {
            log.info("用户登录结果==========" + JSON.toJSONString(result));
        }
        return result;
    }


    /**
     * 用户根据手机号和密码登录
     *
     * @param userPhone 登录手机号
     * @param password  登录密码(MD5 32 小写加密)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginByUserPhone.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))
    @ApiOperation(value = "用户登录", notes = "手机号登录", response = Result.class, httpMethod = "POST", position = 2)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result userLoginByUserPhone(
            @ApiParam(value = "登录手机号", required = true, name = "userPhone", example = "userPhone = 18888888888")
            @RequestParam("userPhone")
                    String userPhone,
            @ApiParam(value = "登录密码(MD5 32位小写加密)", required = true, name = "password", example = "password = e10adc3949ba59abbe56e057f20f883e")
            @RequestParam("password")
                    String password
    ) {
        User user = new User();
        user.setPassword(password);
        user.setUserPhone(userPhone);
        log.info("用户登录");
        log.info("用户登录信息==========" + JSON.toJSONString(user));
        Result result = new Result();
        try {

        } catch (Exception e) {
            errorHandler(e, result);
        } finally {
            log.info("用户登录结果==========" + JSON.toJSONString(result));
        }
        return result;
    }


    /**
     * 用户找回密码
     *
     * @param userName
     * @param userPhone
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reSetPassword.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))

    @ApiOperation(value = "用户重设密码", notes = "用户根据用户名和注册手机号重设密码(用户名与手机号配对成功才可进行重设密码的操作)", response = Result.class, httpMethod = "POST", position = 3)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result reSetPassword(
            @ApiParam(value = "用户名", name = "userName", required = true, example = "userName = zcy")
            @RequestParam("userName")
                    String userName,
            @ApiParam(value = "用户注册手机号", name = "userPhone", required = true, example = "userPhone = 18888888888")
            @RequestParam("userPhone")
                    String userPhone,
            @ApiParam(value = "用户新密码(明文)", name = "password", required = true, example = "password = 123456")
            @RequestParam("password")
                    String password
    ) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setPassword(password);
        log.info("用户根据用户名和手机号重设密码");
        log.info("用户重设密码信息==========" + JSON.toJSONString(user));
        Result result = new Result();
        try {
            userService.reSetPassword(user, result);
        } catch (Exception e) {
            errorHandler(e, result);
        } finally {
            log.info("用户重设密码结果==========" + JSON.toJSONString(result));
        }
        return result;

        User thisUser = userService.getUserByUserName(user.getUserName());
        Result result = new Result();
        if (null == thisUser) {
            result = new Result<User>(null, "该用户不存在", 0);
            return result;
        }
        if (!thisUser.getUserPhone().equals(user.getUserPhone())) {
            result = new Result<User>(null, "用户名与注册手机号不匹配", 0);
            return result;
        }
        if (thisUser.getUserPhone().equals(user.getUserPhone())) {
            try {
                user.setUserId(thisUser.getUserId());
                user.setModifyTime(new Date());
                userService.updatePassword(user);
                thisUser.setPassword(password);
                thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                result = new Result<User>(thisUser, "重设密码成功", 1);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return result;
    }


    /**
     * 用户修改密码
     *
     * @param userId
     * @param password
     * @param newPassword
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassword.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))

    @ApiOperation(value = "用户修改密码", notes = "用户修改密码", response = Result.class, httpMethod = "POST", position = 4)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result<User> updatePassword(
            @ApiParam(value = "用户id", name = "userId", required = true, example = "userId = 9")
            @RequestParam("userId")
                    String userId,
            @ApiParam(value = "用户旧密码(明文)", name = "password", required = true, example = "password = 123456")
            @RequestParam("password")
                    String password,
            @ApiParam(value = "用户新密码(明文)", name = "newPassword", required = true, example = "newPassword = 123456")
            @RequestParam("newPassword")
                    String newPassword,
            @ApiParam(value = "token", name = "token", required = true, example = "token = token")
            @RequestParam("token")
                    String token
    ) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        log.info("用户修改密码");
        log.info("用户修改密码信息==========" + JSON.toJSONString(user));
        User thisUser = userService.getUserById(user);
        Result<User> result = null;
        String thisToken = UUIDUtil.getToken(userId);
        if (!token.equals(thisToken)) {
            result = new Result<User>(null, "token验证失败", -1);
            return result;
        }
        if (null == thisUser) {
            result = new Result<User>(null, "该用户不存在", 0);
            return result;
        }
        if (!thisUser.getPassword().equals(user.getPassword())) {
            result = new Result<User>(null, "旧密码错误", 0);
            return result;
        }
        if (thisUser.getPassword().equals(user.getPassword())) {
            try {
                user.setPassword(newPassword);
                user.setModifyTime(new Date());
                userService.updatePassword(user);
                thisUser.setPassword(password);
                thisUser.setToken(UUIDUtil.getToken(thisUser.getUserId()));
                result = new Result<User>(thisUser, "重设密码成功", 1);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.info("用户修改密码结果==========" + JSON.toJSONString(result));
        return result;
    }


}
