package com.autumn.service.impl;

import com.autumn.dao.PermissionMapper;
import com.autumn.pojo.Permission;
import com.autumn.service.PermissionService;
import com.yunye.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangfu
 */
@Service
public class PermissionServiceImpl extends BaseService<PermissionMapper> implements PermissionService {

    private final PermissionMapper permissionMapper;
    public PermissionServiceImpl(PermissionMapper permissionMapper, PermissionMapper permissionMapper1) {
        super(permissionMapper);
        this.permissionMapper = permissionMapper1;
    }

    @Override
    public List<Permission> findAllPermissionByRoleIds(List<Integer> roleIds) {
        return permissionMapper.findAllPermissionByRoleIds(roleIds);
    }
}
