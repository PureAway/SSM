package com.zcy.ssm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 常用配置
 * Created by zcy on 2016/9/23.
 */
public class CommonConfig {

    private static Properties prop = null;

    public static String rootDir = null;

    static final Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    static {
        logger.info("加载配置文件");
        loadProp();
    }

    private static void loadProp() {
        try {
            prop = new Properties();
            prop.load(CommonConfig.class.getClassLoader().getResourceAsStream(
                    "config.properties"));
        } catch (IOException e) {
            logger.info("load commonConfig failed:" + e.getMessage());
        }
    }

    /**
     * 获取图片地址
     **/
    public static String getImageAddress() {
        return prop.getProperty("images", "");
    }

    /**
     * 获取图片地址
     **/
    public static String getPublicAddress() {
        return prop.getProperty("image.address", "");
    }

}
