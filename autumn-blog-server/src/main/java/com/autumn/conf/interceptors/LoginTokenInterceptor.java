package com.autumn.conf.interceptors;

import com.autumn.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录的拦截器
 * @author huangfu
 */
@Component
@Slf4j
public class LoginTokenInterceptor implements HandlerInterceptor {

    private final TokenUtil tokenUtil;
    @Value("${token.name}")
    private String tokenName;

    public LoginTokenInterceptor(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-------------登录拦截器-----------------");
        String token = request.getHeader(this.tokenName);
        if(StringUtils.isNoneBlank(token)){
            boolean loginValidate = tokenUtil.checkTokenReturnUid(token);
            if (loginValidate) {
                return true;
            }
        }
        response.setStatus(401);
        return false;
    }
}
