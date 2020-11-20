package com.lwh.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "9d5b364d";

    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDbPass(String formPass,String salt){
        String str =""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass,saltDB);
        return dbPass;
    }

}
