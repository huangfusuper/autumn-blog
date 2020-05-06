package com.autumn.dao;

import com.autumn.pojo.LeftMenu;
import com.yunye.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限菜单
 * @author huangfu
 */
@Repository
public interface LeftMenuMapper extends BaseMapper {
    /**
     * 根据角色id查询所有的菜单权限
     * @param roleIds 角色
     * @return 菜单
     */
    List<LeftMenu> findAllMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
