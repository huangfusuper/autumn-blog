package com.autumn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String nickName;
    private String userName;
    private String password;
    private String salt;
    private String email;
    private String address;
}
