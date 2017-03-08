package com.phoenix.qingmiaodanxie.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 王东 on 2017/3/2.
 */

public class MD5Utils {
    public static void main(String[] args)  {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            String password = "123456";
//        String password = "e1adc3949ba59abbe56e057f2f883e";

            byte[] bytes = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (byte b:bytes) {
                //6 10
                //00000110
                //00001010
                //00000010 与运算
                //00001110 或运算
//            int number = b & 0xff;
                int number = b & 0xfff;//加盐
                String numberStr = Integer.toHexString(number);
                System.out.print(numberStr);
                if (numberStr.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(numberStr);
            }
            System.out.print(buffer.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
