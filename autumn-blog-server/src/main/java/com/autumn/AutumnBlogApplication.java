package com.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 启动类
 * @author huangfu
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.autumn.dao")
public class AutumnBlogApplication {
    public static void main( String[] args ) {
        SpringApplication.run(AutumnBlogApplication.class,args);
    }
    private CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source  = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration());
        return new CorsFilter(source);
    }

}
