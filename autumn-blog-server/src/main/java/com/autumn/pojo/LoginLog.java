package com.autumn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog implements Serializable {
    private Integer id;
    private String address;
    private String os;
    private String ip;
    private String browserType;
    private Date loginTime;
}
