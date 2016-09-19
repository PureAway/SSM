package com.zcy.ssm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * Created by zcy on 2016/9/18.
 */
public class MD5Util {

    public static String MD5(String plainText) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // result = buf.toString();  //md5 32bit
            // result = buf.toString().substring(8, 24))); //md5 16bit
            result = buf.toString();
            System.out.println("md5 16bit: " + buf.toString().substring(8, 24));
            System.out.println("md5 32bit: " + buf.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


}
