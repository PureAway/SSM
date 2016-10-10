package com.zcy.ssm.service.serviceImpl;

import com.zcy.ssm.base.dto.Result;
import com.zcy.ssm.config.CommonConfig;
import com.zcy.ssm.dao.FileDao;
import com.zcy.ssm.service.FileService;
import com.zcy.ssm.utils.TextUtils;
import com.zcy.ssm.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcy on 2016/9/23.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    public void upLoadFile(@RequestParam("file") MultipartFile file, Map<String, Object> paramsMap, Result result) {
        File targetFile = null;
        String fileName = null;
        String path = null;
        String userId = paramsMap.get("userId").toString();
        String suffix = paramsMap.get("suffix").toString();
        String token = paramsMap.get("token").toString();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            if (!token.equals(UUIDUtil.getToken(userId))) {
                result.setResult(null, "token验证失败", -1);
                return;
            }
            if (TextUtils.isEmpty(suffix)) {
                result.setResult(null, "文件的后缀名不能为空", 0);
                return;
            }
            if (file != null && !file.isEmpty()) {
                System.out.println("========serId===" + userId);
                System.out.println("========suffix===" + suffix);
                System.out.println("========token===" + token);
                fileName = UUIDUtil.getUUIDString() + "." + suffix;
                System.out.println("===fileName==" + fileName);
                paramsMap.put("userHeadImg", fileName);
                if (CommonConfig.rootDir.trim().endsWith(File.separator)) {
                    path = CommonConfig.rootDir + CommonConfig.getImageAddress();
                } else {
                    path = CommonConfig.rootDir + File.separator + CommonConfig.getImageAddress();
                }
                targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                System.out.println("====保存文件完成====");
                fileDao.updateUserHeadPhoto(paramsMap);
                response.put("userHeadImg", CommonConfig.getPublicAddress() + CommonConfig.getImageAddress() + fileName);
                System.out.println("===数据库更新=====返回图片地址" + response.get("userHeadImg"));
                result.setResult(response, "修改成功", 1);
            } else {
                System.out.println("===无头像文件==");
                result.setResult(null, "无文件信息，上传失败", 0);
            }
        } catch (Exception e) {
            result.setResult(null, "内部服务器错误，上传失败", 0);
            System.out.println("=====upLoadFile.ee=" + e.toString());
        }
    }
}
