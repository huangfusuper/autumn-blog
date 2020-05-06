package com.autumn.pojo;

import com.autumn.dto.UserLoginDto;
import com.autumn.utils.UserUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类
 * @author huangfu
 */
@Data
public class BaseEntity implements Serializable {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String updateAuthor;
    private String createAuthor;

    /**
     * 创建前置条件
     */
    public void preCreate(){
        UserLoginDto userLogin = UserUtil.getUserLogin();
        this.createAuthor = userLogin.getUser().getUserName();
        this.createTime = new Date();
    }

    /**
     * 修改前置条件
     */
    public void preUpdate(){
        UserLoginDto userLogin = UserUtil.getUserLogin();
        this.updateAuthor = userLogin.getUser().getUserName();
        this.updateTime = new Date();
    }
}
