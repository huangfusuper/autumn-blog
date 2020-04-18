package com.autumn.service.impl;

import com.autumn.dao.UserMapper;
import com.autumn.dto.UserLoginDto;
import com.autumn.pojo.User;
import com.autumn.service.UserService;
import com.autumn.utils.TokenUtil;
import com.yunye.help.SqlGenerateHelp;
import com.yunye.help.criteria.Criteria;
import com.yunye.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务操作
 * @author huangfu
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper> implements UserService {
    private final TokenUtil tokenUtil;
    public UserServiceImpl(UserMapper dao, TokenUtil tokenUtil) {
        super(dao);
        this.tokenUtil = tokenUtil;
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
                //查询角色对应的权限
                String token = tokenUtil.genToken(userLoginDto);
            }
        }
        return null;
    }
}
