package com.autumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 * @author huangfu
 */
@SpringBootApplication
public class AutumnBlogApplication {
    public static void main( String[] args ) {
        SpringApplication.run(AutumnBlogApplication.class,args);
    }
}
