package com.autumn.controller;

import com.autumn.dto.UserLoginDto;
import com.autumn.utils.RedisCacheUtil;
import com.autumn.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangfu
 */
@RestController
public class TestController {
    @Value("${token.secret}")
    private String secret;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @RequestMapping("test")
    public UserLoginDto test(){
        String token = tokenUtil.genToken();
        System.out.println(token);
        UserLoginDto cacheObject = redisCacheUtil.getCacheObject(token, UserLoginDto.class);
        return cacheObject;
    }

    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey("56eL5aSp5piv5LiA5Liq5b6I5aW955qE5a2j6IqCLOaIkeW+iOWWnOasou+8geS4jeWGt+S4jeeDre+8gQ==")
                //设置需要解析的jwt
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJsb2dpblRva2VuIjoiZmFmODQ4NWMtMjA4Yi00OTVjLTg2NjAtYmRlZGUzOGIwODQyIiwic3ViIjoiYmxvZyIsImlhdCI6MTU4NzA0NDMzMSwianRpIjoiZGY1ZTUxYTMtNzhiNC00Mzk5LTg2MmEtMTI0ZjkxYzc0NDZjIn0.qX6eextIgsXrzibyqOxibDdZqsSethFPOhYBLKOPV68")
                .getBody();
        String loginToken = claims.get("loginToken").toString();

        System.out.println(loginToken);
    }
}
