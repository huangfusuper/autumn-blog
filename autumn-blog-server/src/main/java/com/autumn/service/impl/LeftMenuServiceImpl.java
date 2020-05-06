package com.autumn.service.impl;

import com.autumn.dao.LeftMenuMapper;
import com.autumn.pojo.LeftMenu;
import com.autumn.service.LeftMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangfu
 */
@Service
public class LeftMenuServiceImpl implements LeftMenuService {
    private final LeftMenuMapper leftMenuMapper;

    public LeftMenuServiceImpl(LeftMenuMapper leftMenuMapper) {
        this.leftMenuMapper = leftMenuMapper;
    }

    @Override
    public List<LeftMenu> findRoleAllMenu(List<Integer> roleIds) {
        return leftMenuMapper.findAllMenuByRoleIds(roleIds);
    }
}
