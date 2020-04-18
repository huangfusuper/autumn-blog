package com.autumn.service;

import com.autumn.pojo.User;

import java.util.List;

/**
 * 用户业务
 * @author huangfu
 */
public interface UserService {
    /**
     * 查询全部的用户
     * @return 全部的用户
     */
    List<User> findAllUser();

    /**
     * 根据id查询user
     * @param id 用户id
     * @return 用户
     */
    User findUserById(Integer id);

    /**
     * 查询用户  根据用户名称
     * @param userName 用户名
     * @return 返回对应的用户
     */
    User findUserByUserName(String userName);

    /**
     * 根据用户名和密码查询
     * @param userName
     * @param password
     * @return
     */
    User findUserByUserNameAndPassword(String userName,String password);

    /**
     * 登录逻辑
     * @param userName 用户账号
     * @param password 用户密码
     * @param code 验证码
     * @return
     */
    String login(String userName,String password,String code);
}
