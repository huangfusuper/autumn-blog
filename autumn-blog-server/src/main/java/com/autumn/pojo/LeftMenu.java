package com.autumn.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 左侧菜单栏
 * @author huangfu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeftMenu extends BaseEntity {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 跳转的路由
     */
    private String menuRouter;
    /**
     * 是否激活 0启用  1 禁用
     */
    private String active;
    /**
     * 是否隐藏  0显示  1隐藏
     */
    private String hide;
    /**
     * 菜单的类型 0目录 1菜单 2按钮
     */
    private String menuType;
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 菜单标识
     */
    private String icon;
    /**
     * 菜单备注
     */
    private String remarks;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限标识
     */
    private String permissionMark;
    /**
     * 权限详情
     */
    private String permissionDetails;
}
