package com.autumn.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录数据的载体
 * @author huangfu
 */
@Data
public class LoginDataDto implements Serializable {
    @NotNull(message = "用户名称不能为空")
    private String userName;
    @NotNull(message = "用户密码不能为空")
    private String password;
    private String code;
}
