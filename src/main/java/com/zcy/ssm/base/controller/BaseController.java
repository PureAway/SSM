package com.zcy.ssm.base.controller;

import com.zcy.ssm.base.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller 基类
 * Created by zcy on 2016/9/14.
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected void errorHandler(Exception e, Result result) {
        if (result == null) {
            result = new Result();
            result.setReturnCode(0);
        }
        if (e instanceof java.net.SocketTimeoutException) {
            result.setReturnMessage("接口访问超时！");
        } else {
            result.setReturnMessage("系统处理异常");
        }
        log.error(result.getReturnMessage(), e);
    }

}
