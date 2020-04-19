package com.autumn.dao;

import com.autumn.pojo.Role;
import com.yunye.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层
 * @author huangfu
 */
@Repository
public interface RoleMapper extends BaseMapper {
    /**
     * 查询角色 根据用户id查询
     * @param userId 用户的id
     * @return 返回用户所拥有的角色
     */
    List<Role> findAllRoleByUserId(Integer userId);
}
