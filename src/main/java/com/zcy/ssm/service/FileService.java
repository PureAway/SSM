package com.zcy.ssm.service;

import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.base.service.BaseService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by zcy on 2016/9/23.
 */
public interface FileService extends BaseService {

    /**
     * 用户上传头像
     *
     * @param file      上传的头像文件
     * @param paramsMap 其他参数
     * @param result    上传结果
     */
    void upLoadFile(@RequestParam("file") MultipartFile file, Map<String, Object> paramsMap, Result result);

    void upLoadOtherFiles(@RequestParam("files") MultipartFile[] files, Map<String, Object> paramsMap, Result result);

}
