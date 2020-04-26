package com.autumn;

import com.autumn.enums.UnknownEnum;
import com.yunye.common.enums.IEnum;
import lombok.Data;

/**
 * 结果集封装
 * @author huangfu
 */
@Data
public class AutumnResponse {

    private String code;
    private String msg;
    private Object data;

    public AutumnResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AutumnResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AutumnResponse ok(){
        return new AutumnResponse(UnknownEnum.SUCCESS.getCode(),UnknownEnum.SUCCESS.getMsg());
    }
    public static AutumnResponse ok(Object data){
        return new AutumnResponse(UnknownEnum.SUCCESS.getCode(),UnknownEnum.SUCCESS.getMsg(),data);
    }

    public static AutumnResponse error(){
        return new AutumnResponse(UnknownEnum.UNKNOWN_EXCEPTION.getCode(),UnknownEnum.UNKNOWN_EXCEPTION.getMsg());
    }

    public static AutumnResponse error(IEnum iEnum){
        return new AutumnResponse(iEnum.getCode(),iEnum.getMsg());
    }
}
