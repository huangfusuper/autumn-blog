package com.yunye.common.dto;

import com.autumn.pojo.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户登录信息
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto implements Serializable {
    /**
     * 用户使用的浏览器标识
     */
    private String userAgent;

    /**
     * 登录归属地
     */
    private String address;
    /**
     * 用户的唯一标识
     */
    private String token;
    /**
     * 用户的操作系统
     */
    private String os;
    /**
     * 用户的ip
     */
    private String ip;

    /**
     * 浏览器类型
     */
    private String browserType;
    /**
     * 登录时间
     */
    private Long loginTime;
    /**
     * 到期时间
     */
    private Long expireTime;

    /**
     * 对应的用户详细信息  这个东西是需要每次放到本地缓存里面的
     */
    private LoginUser loginUser;
    /**
     * 用户权限的集合
     */
    private Set<String> permissions;
}
