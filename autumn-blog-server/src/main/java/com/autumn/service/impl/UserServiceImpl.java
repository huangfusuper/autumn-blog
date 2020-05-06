package com.autumn.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.autumn.dao.UserMapper;
import com.autumn.dto.LoginDataDto;
import com.autumn.dto.UserLoginDto;
import com.autumn.enums.exception.UserExceptionEnum;
import com.autumn.exceptions.AutumnException;
import com.autumn.pojo.LeftMenu;
import com.autumn.pojo.Role;
import com.autumn.pojo.User;
import com.autumn.service.LeftMenuService;
import com.autumn.service.RoleService;
import com.autumn.service.UserService;
import com.autumn.utils.TokenUtil;
import com.yunye.help.SqlGenerateHelp;
import com.yunye.help.criteria.Criteria;
import com.yunye.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private final LeftMenuService leftMenuService;
    public UserServiceImpl(UserMapper dao, TokenUtil tokenUtil, RoleService roleService, LeftMenuService leftMenuService) {
        super(dao);
        this.tokenUtil = tokenUtil;
        this.roleService = roleService;
        this.leftMenuService = leftMenuService;
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
    public String login(LoginDataDto loginDataDto) {
        User userByUserName = findUserByUserName(loginDataDto.getUserName());
        if (userByUserName != null) {
            boolean successLogin = DigestUtil.bcryptCheck(loginDataDto.getPassword(), userByUserName.getPassword());
            if(successLogin) {
                UserLoginDto userLoginDto = new UserLoginDto();
                userLoginDto.setUser(userByUserName);
                //查询对应的角色
                List<Role> allRoleByUserId = roleService.findAllRoleByUserId(userByUserName.getId());
                //查询角色对应的权限
                //1.筛选出这些角色的id
                //2.根据角色id in查询权限
                List<Integer> roleIds = allRoleByUserId.stream().map(Role::getId).collect(Collectors.toList());
                List<LeftMenu> allPermissionByRoleIds = leftMenuService.findRoleAllMenu(roleIds);
                Set<LeftMenu> menuSet = new HashSet<LeftMenu>();
                menuSet.addAll(allPermissionByRoleIds);
                userLoginDto.setLeftMenus(menuSet);
                return tokenUtil.genToken(userLoginDto);
            }
        }
        throw new AutumnException(UserExceptionEnum.LOGIN_USERNAME_PASSWORD_ERROR);
    }
}
