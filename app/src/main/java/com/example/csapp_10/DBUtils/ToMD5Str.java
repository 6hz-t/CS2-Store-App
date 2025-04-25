package com.example.csapp_10.DBUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ToMD5Str {
    public static String toMD5(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte []digest=null;
        //返回加密的MD5值
        MessageDigest md=MessageDigest.getInstance("md5");
        digest= md.digest(plainText.getBytes("utf-8"));
        String md5str=new BigInteger(1,digest).toString(16);
        return md5str;
    }
}
