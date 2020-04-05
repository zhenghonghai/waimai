package com.redsea.waimai.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    REGISTER_FAIL(10000, "用户已经存在"),
    LOGIN_FAIL(10001, "登录失败，用户不存在"),
    CHANGE_PASSWORD_FAIL(10002, "修改密码失败，用户不存在"),
    FIND_PASSWORD_FAIL(10003, "找回密码失败"),
    ;

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
