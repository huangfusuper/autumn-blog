package com.autumn.service;

import com.autumn.pojo.Role;

import java.util.List;

/**
 * 角色业务类
 * @author huangfu
 */
public interface RoleService {
    /**
     * 查询角色 根据用户id查询
     * @param userId 用户的id
     * @return 返回用户所拥有的角色
     */
    List<Role> findAllRoleByUserId(Integer userId);
}
