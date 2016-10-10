package com.zcy.ssm.utils;

import sun.misc.BASE64Encoder;

import java.util.UUID;


/**
 * UUID
 * Created by zcy on 2016/9/18.
 */
public class UUIDUtil {

    private static final String UUIDString = "0d6c8b26-72c8-47a2-bf93-2669c6da8d4b";


    /**
     * 根据用户Id生成唯一的字符串作为token
     *
     * @param UserId
     * @return
     */
    public static String getToken(String UserId) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode((UUIDString + UserId).getBytes());
    }

    public static String getUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
