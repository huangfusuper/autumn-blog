package com.autumn.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huangfu
 */
public class ServletUtil {
    /**
     * 获取本次Servlet请求的属性
     * 他是如何知道本次请求的
     * 他实现了Servlet的一个过滤器，每次都把servlet的属性放置到ThreadLocal类里面
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取User-Agent
     *
     * @return User-Agent
     */
    public static String getUserAgent() {
        return getRequestAttributes().getRequest().getHeader("User-Agent");
    }
}
