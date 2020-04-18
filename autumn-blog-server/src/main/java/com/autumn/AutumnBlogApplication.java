package com.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 * @author huangfu
 */
@SpringBootApplication
@MapperScan("com.autumn.dao")
public class AutumnBlogApplication {
    public static void main( String[] args ) {
        SpringApplication.run(AutumnBlogApplication.class,args);
    }
}
