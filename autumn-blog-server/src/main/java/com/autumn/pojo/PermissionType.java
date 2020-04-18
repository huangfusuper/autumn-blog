package com.autumn.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huangfu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionType extends BaseEntity {
    private String name;
    private String details;
}
