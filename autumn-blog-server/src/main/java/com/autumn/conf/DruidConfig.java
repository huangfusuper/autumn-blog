package com.autumn.conf;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.autumn.conf.properties.DruidProperties;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.sql.SQLException;

/**
 * druid配置
 * @author huangfu
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DruidConfig {

    private final DruidProperties druidProperties;

    public DruidConfig(DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }

    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow", druidProperties.getAllow());
        //黑名单
        servletRegistrationBean.addInitParameter("deny",druidProperties.getDeny());
        //超级管理员 用户名 密码
        servletRegistrationBean.addInitParameter("loginUsername", druidProperties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", druidProperties.getLoginPassword());
        //是否能够重置数据  禁用HTML的 reset all 功能
        servletRegistrationBean.addInitParameter("resetEnable",druidProperties.getResetEnable());
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions",".js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }



    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druidProperties.getUrl());
        druidDataSource.setUsername(druidProperties.getUsername());
        druidDataSource.setPassword(druidProperties.getPassword());
        druidDataSource.setFilters(druidProperties.getFilters());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
        druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setAsyncInit(druidProperties.getAsyncInit());
        return druidDataSource;
    }

    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }
    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.autumn.dao.*","com.autumn.service.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

    @Bean
    public Slf4jLogFilter logFilter () {

        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setStatementExecutableSqlLogEnable(true);
        logFilter.setStatementLogEnabled(false);
        return logFilter;
    }

    @Bean
    public StatFilter statFilter () {

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * sql防火墙过滤器配置
     * @param wallConfig 墙配置
     * @return 过滤器
     */
    @Bean
    public WallFilter wallFilter (WallConfig wallConfig) {

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        //对被认为是攻击的SQL进行LOG.error输出
        wallFilter.setLogViolation(true);
        //对被认为是攻击的SQL抛出SQLException
        wallFilter.setThrowException(false);
        return wallFilter;
    }

    /**
     * sql防火墙配置
     * @return 防火墙配置对象
     */
    @Bean
    public WallConfig wallConfig () {

        WallConfig wallConfig = new WallConfig();
        wallConfig.setAlterTableAllow(false);
        wallConfig.setCreateTableAllow(false);
        wallConfig.setDeleteAllow(false);
        wallConfig.setMergeAllow(false);
        wallConfig.setDescribeAllow(false);
        wallConfig.setShowAllow(false);
        return wallConfig;
    }
}
