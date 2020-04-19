package com.autumn.service.impl;

import com.autumn.dao.UserMapper;
import com.autumn.dto.UserLoginDto;
import com.autumn.pojo.Permission;
import com.autumn.pojo.Role;
import com.autumn.pojo.User;
import com.autumn.service.PermissionService;
import com.autumn.service.RoleService;
import com.autumn.service.UserService;
import com.autumn.utils.TokenUtil;
import com.yunye.help.SqlGenerateHelp;
import com.yunye.help.criteria.Criteria;
import com.yunye.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户业务操作
 * @author huangfu
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper> implements UserService {
    private final TokenUtil tokenUtil;
    private final RoleService roleService;
    private final PermissionService permissionService;
    public UserServiceImpl(UserMapper dao, TokenUtil tokenUtil, RoleService roleService, PermissionService permissionService) {
        super(dao);
        this.tokenUtil = tokenUtil;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public List<User> findAllUser() {
        SqlGenerateHelp sqlGenerateHelp = new SqlGenerateHelp(User.class);
        return super.baseFindList(sqlGenerateHelp, User.class);
    }

    @Override
    public User findUserById(Integer id) {
        SqlGenerateHelp sqlGenerateHelp = new SqlGenerateHelp(User.class);
        return super.baseFindOne(sqlGenerateHelp,User.class);
    }

    @Override
    public User findUserByUserName(String userName) {
        SqlGenerateHelp sqlGenerateHelp = new SqlGenerateHelp(User.class);
        Criteria criteria = sqlGenerateHelp.createCriteria();
        criteria.andEqualTo("user_name",userName);
        return super.baseFindOne(sqlGenerateHelp, User.class);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        SqlGenerateHelp sqlGenerateHelp = new SqlGenerateHelp(User.class);
        Criteria criteria = sqlGenerateHelp.createCriteria();
        criteria.andEqualTo("user_name",userName)
        .andEqualTo("password",password);
        return super.baseFindOne(sqlGenerateHelp,User.class);
    }

    @Override
    public String login(String userName, String password, String code) {
        User userByUserName = findUserByUserName(userName);
        if (userByUserName != null) {
            String salt = userByUserName.getSalt();
            //TODO 加解密
            User loginUser = findUserByUserNameAndPassword(userName, password);
            if(null != loginUser) {
                UserLoginDto userLoginDto = new UserLoginDto();
                userLoginDto.setUser(loginUser);
                //查询对应的角色
                List<Role> allRoleByUserId = roleService.findAllRoleByUserId(loginUser.getId());
                //查询角色对应的权限
                //1.筛选出这些角色的id
                //2.根据角色id in查询权限
                List<Integer> roleIds = allRoleByUserId.stream().map(Role::getId).collect(Collectors.toList());
                List<Permission> allPermissionByRoleIds = permissionService.findAllPermissionByRoleIds(roleIds);
                Set<String> markName = allPermissionByRoleIds.stream().map(Permission::getMarkName).collect(Collectors.toSet());
                userLoginDto.setPermissions(markName);
                return tokenUtil.genToken(userLoginDto);
            }
        }
        return null;
    }
}
