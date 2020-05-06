package com.autumn.service;

import com.autumn.pojo.LeftMenu;

import java.util.List;

/**
 * @author huangfu
 */
public interface LeftMenuService {
    /**
     * 角色对应的所有菜单
     * @param roleIds 角色id
     * @return 菜单集合
     */
    List<LeftMenu> findRoleAllMenu(List<Integer> roleIds);
}
