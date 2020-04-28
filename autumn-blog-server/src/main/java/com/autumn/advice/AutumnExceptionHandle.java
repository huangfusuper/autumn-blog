package com.autumn.advice;

import com.yunye.common.enums.IEnum;
import com.yunye.common.exception.IException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常统一封装入口
 * @author huangfu
 */
@RestControllerAdvice
public class AutumnExceptionHandle {

    @ExceptionHandler(value = {Exception.class})
    public AutumnResponse requestException(Exception e){
        e.printStackTrace();
        if(e instanceof IException) {
            return parseException(e);
        }
        return AutumnResponse.error();
    }

    public AutumnResponse parseException(Exception e){
        IException iException = (IException) e;
        IEnum iEnum = iException.getIEnum();
        return AutumnResponse.error(iEnum);
    }
}
