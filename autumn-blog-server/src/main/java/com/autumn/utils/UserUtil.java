package com.autumn.utils;

import com.autumn.dto.UserLoginDto;

/**
 * 获取user的工具类
 * @author huangfu
 */
public class UserUtil {
    private static ThreadLocal<UserLoginDto> userLoginDtoThreadLocal = new ThreadLocal<>();

    public static void setUserLogin(UserLoginDto userLoginDto) {
        userLoginDtoThreadLocal.set(userLoginDto);
    }

    public static UserLoginDto getUserLogin () {
        return userLoginDtoThreadLocal.get();
    }
}
