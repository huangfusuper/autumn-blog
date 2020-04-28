package com.autumn.conf;

import com.autumn.conf.interceptors.LoginTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的配置
 * @author huangfu
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    private final LoginTokenInterceptor loginTokenInterceptor;

    public LoginInterceptorConfig(LoginTokenInterceptor loginTokenInterceptor) {
        this.loginTokenInterceptor = loginTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
