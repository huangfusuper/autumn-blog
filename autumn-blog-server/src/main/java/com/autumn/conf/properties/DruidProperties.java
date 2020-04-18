package com.autumn.conf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 映射druid的配置文件
 * @author huangfu
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {
    private String driverClassName;
    private String username;
    private String password;
    private String url;
    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxWait;
    private String validationQuery;
    private Integer validationQueryTimeout;
    private Boolean testWhileIdle;
    private Boolean asyncInit;
    private Integer minEvictableIdleTimeMillis;
    private String filters;
    private String resetEnable;
    private String loginUsername;
    private String loginPassword;
    private String allow;
    private String deny;
}
