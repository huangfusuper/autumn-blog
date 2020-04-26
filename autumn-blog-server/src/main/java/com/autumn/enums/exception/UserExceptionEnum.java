package com.autumn.enums.exception;

/**
 * @author huangfu
 */

import com.yunye.common.enums.IEnum;

/**
 * 用户相关异常 1xxxxx 开头 后续五位编号
 * 用户异常枚举
 * @author huangfu
 */
public enum UserExceptionEnum implements IEnum {
    LOGIN_USERNAME_PASSWORD_ERROR("100000","用户名或密码错误！")
    ;
    private String code;
    private String msg;

    UserExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
