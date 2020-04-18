package com.autumn.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限表
 * @author huangfu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Permission extends BaseEntity {
    private String name;
    private String details;
    private String markName;
    private Integer type;
}
