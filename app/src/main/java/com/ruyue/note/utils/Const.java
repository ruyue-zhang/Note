package com.ruyue.note.utils;

public class Const {

    public static final String URL = "https://twc-android-bootcamp.github.io/fake-data/data/user.json";
    public static final int USER_NOT_EXIST= 0;
    public static final int PASSWORD_NOT_CORRECT= 1;
    public static final int LOGIN_SUCCEED= 2;
    public static final String IS_LOGIN = "loginStatus";
    public static final String NAME_PATTERN = "^[a-zA-Z0-9]{3,12}$";
    public static final String PASSWORD_PATTERN = "^.{6,18}$";
    public static final String SET_NAME_ERROR = "用户名必须是3 ~ 12为字母或数字";
    public static final String SET_PASSWORD_ERROR = "密码长度必须是6 ~ 18位字符";
    public static final String ERROR_NAME = "用户不存在";
    public static final String ERROR_PASSWORD = "密码错误";
}
