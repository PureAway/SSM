package com.zcy.ssm.utils;

/**
 * 字符串操作工具类
 * Created by zcy on 2016/9/20.
 */
public class TextUtils {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

}
