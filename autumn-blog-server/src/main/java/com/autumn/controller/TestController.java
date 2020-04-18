package com.autumn.controller;

import com.alibaba.fastjson.JSONObject;
import com.autumn.dto.UserLoginDto;
import com.autumn.pojo.User;
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

    @RequestMapping("test")
    public UserLoginDto test(){
        log.error("-------------皇甫科星----");
        log.debug("-------------皇甫科星----");
        log.warn("-------------皇甫科星----");
        log.debug("-------------皇甫科星----");
        log.debug("-------------皇甫科星----");
        log.info("-------------皇甫科星----");
        User user = new User();
        user.setAddress("河南省");
        String s1 = tokenUtil.genToken(user);
        String s = tokenUtil.checkTokenReturnUid(s1);

        JSONObject cacheObject = redisCacheUtil.getCacheObject(s);
        UserLoginDto userLoginDto = cacheObject.toJavaObject(UserLoginDto.class);
        return userLoginDto;
    }
}
