package com.autumn.pojo;

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

    public void preCreate(){
        this.createTime = new Date();
    }

    public void preUpdate(){
        this.updateTime = new Date();
    }
}
