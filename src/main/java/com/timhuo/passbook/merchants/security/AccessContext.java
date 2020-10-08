package com.timhuo.passbook.merchants.security;

/**
 * @description: 用ThreadLocal去单独存储每一个线程携带的部分信息
 * @author: Tim_Huo
 * @created: 2020/10/08 06:06
 */
public class AccessContext {

    private static final ThreadLocal<String> TOKEN = new ThreadLocal<String>();

    public static String getToken() {
        return TOKEN.get();
    }

    public static void setToken(String token) {
        TOKEN.set(token);
    }

    public static void clearAccessKey() {
        TOKEN.remove();
    }
}
