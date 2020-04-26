package com.autumn.exceptions;

import com.yunye.common.enums.IEnum;
import com.yunye.common.exception.IException;

/**
 * 博客异常
 * @author huangfu
 */
public class AutumnException extends RuntimeException implements IException {
    private IEnum iEnum;

    public AutumnException(IEnum iEnum) {
        super(iEnum.getMsg());
        this.iEnum = iEnum;
    }

    public AutumnException(Throwable e){
        super(e);
    }

    @Override
    public IEnum getIEnum() {
        return iEnum;
    }
}
