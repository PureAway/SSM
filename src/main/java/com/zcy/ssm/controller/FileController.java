package com.zcy.ssm.controller;

import com.zcy.ssm.base.controller.BaseController;
import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.config.CommonConfig;
import com.zcy.ssm.service.FileService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zcy on 2016/9/23.
 */
@Api(value = "/file", description = "文件上传相关接口", position = 1)
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/upLoadUserHeadImg.do", method = RequestMethod.POST, produces = ("application/json;charset=UTF-8"))
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像", response = Result.class, httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数", response = Result.class),
    })
    private Result upLoadUserHeadImg(
            @ApiParam(value = "上传的文件", required = true, name = "file")
            @RequestParam("file")
                    MultipartFile file,
            HttpServletRequest request,
            @ApiParam(value = "用户id", name = "userId", required = true, example = "userId = 9")
            @RequestParam("userId")
                    String userId,
            @ApiParam(value = "token", name = "token", required = true, example = "token = token")
            @RequestParam("token")
                    String token,
            @ApiParam(value = "文件后缀名", name = "suffix", required = true, example = "suffix = png")
            @RequestParam("suffix")
                    String suffix
    ) {
        CommonConfig.rootDir = request.getServletContext().getRealPath("/");
        log.info("=================begin 上传文件====================根路径" + CommonConfig.rootDir);
        Result result = new Result();
        try {
            fileService.upLoadFile(file, getParamMap(request), result);
        } catch (Exception e) {
            errorHandler(e, result);
        } finally {
            log.info("==================end 上传文件=======================");
        }
        return result;
    }

}
