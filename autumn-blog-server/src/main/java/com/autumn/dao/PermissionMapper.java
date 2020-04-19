package com.autumn.dao;

import com.autumn.pojo.Permission;
import com.yunye.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层操作
 * @author huangfu
 */
@Repository
public interface PermissionMapper extends BaseMapper {
    /**
     * 查询全部 权限根据角色Id
     * @param roleIds 角色ids
     * @return 查询权限
     */
    List<Permission> findAllPermissionByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
