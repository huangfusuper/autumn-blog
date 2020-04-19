package com.autumn.controller;

import com.alibaba.fastjson.JSONObject;
import com.autumn.dto.UserLoginDto;
import com.autumn.pojo.User;
import com.autumn.service.UserService;
import com.autumn.utils.RedisCacheUtil;
import com.autumn.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangfu
 */
@RestController
@Slf4j
public class TestController {
    @Value("${token.secret}")
    private String secret;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public UserLoginDto test(){
        String login = userService.login("123", "123", "123");
        String uid = tokenUtil.checkTokenReturnUid(login);
        JSONObject cacheObject = redisCacheUtil.getCacheObject(uid);
        return cacheObject.toJavaObject(UserLoginDto.class);
    }
}
