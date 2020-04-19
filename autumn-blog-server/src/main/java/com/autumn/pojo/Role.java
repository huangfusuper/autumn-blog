package com.autumn.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体创建
 * @author huangfu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    private String name;
    private String details;
}
