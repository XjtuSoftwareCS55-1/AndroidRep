package com.example.abner.sleepafter15min.DAO;

/**
 * Created by abner on 2018/6/3.
 */

public class WebServer {
    private static String site = "http://47.97.212.158:8080/web";
    public static String getSite(){
        return site;
    }
    public static String getUser(){
        return site + "/user";
    }
    public static  String getSignUp() {
        return site + "/user/add";
    }
}
