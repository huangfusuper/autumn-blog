package com.autumn.service.impl;

import com.autumn.dao.RoleMapper;
import com.autumn.pojo.Role;
import com.autumn.service.RoleService;
import com.yunye.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现
 * @author huangfu
 */
@Service
public class RoleServiceImpl extends BaseService<RoleMapper> implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        super(roleMapper);
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> findAllRoleByUserId(Integer userId) {
        return roleMapper.findAllRoleByUserId(userId);
    }
}
