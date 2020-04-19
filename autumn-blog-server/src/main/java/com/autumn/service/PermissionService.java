package com.autumn.service;

import com.autumn.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限的业务操作
 * @author huangfu
 */
public interface PermissionService {

    /**
     * 查询全部 权限根据角色Id
     * @param roleIds 角色ids
     * @return 查询权限
     */
    List<Permission> findAllPermissionByRoleIds(List<Integer> roleIds);
}
