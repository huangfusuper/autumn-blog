package com.autumn.enums;

import com.yunye.common.enums.IEnum;

/**
 * 通用的枚举定义
 * @author huangfu
 */
public enum UnknownEnum implements IEnum {
    UNKNOWN_EXCEPTION("500000","发生未知错误，请联系管理员！"),
    SUCCESS("000000","成功")
    ;
    private String code;
    private String msg;

    UnknownEnum(String code, String msg) {
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
