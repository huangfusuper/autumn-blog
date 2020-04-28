package com.autumn.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局结果集封装
 * @author huangfu
 */
@RestControllerAdvice
public class AutumnResultAdvice implements ResponseBodyAdvice {
    /**
     * 何时拦截结果进行封装
     * @param methodParameter 参数
     * @param aClass 拦截对象
     * @return 是否拦截
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 具体的拦截逻辑
     * @param body 结果集
     * @param methodParameter 方法参数
     * @param mediaType 请求类型
     * @param aClass 类对象
     * @param serverHttpRequest request
     * @param serverHttpResponse response
     * @return 封装后的结果集
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(body instanceof AutumnResponse){
            return body;
        }
        if(body instanceof String){
            return JSON.toJSONString(AutumnResponse.ok(body));
        }
        return AutumnResponse.ok(body);
    }
}
