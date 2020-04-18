package com.autumn.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.autumn.common.CommonConstant;
import com.autumn.dto.IpAddressDto;
import com.autumn.dto.UserLoginDto;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 操作token
 * @author huangfu
 */
@Component
public class TokenUtil {

    private final static  Integer REF_TOKEN_TIME = 600000;
    @Value("${token.secret}")
    private String secret;

    @Value("${token.uid}")
    private String uId;

    @Value("${token.timeout}")
    private Integer timeout;

    @Value("${ip.appKey}")
    private String appKey;

    private final RedisCacheUtil redisCacheUtil;

    public TokenUtil(RedisCacheUtil redisCacheUtil) {
        this.redisCacheUtil = redisCacheUtil;
    }

    public String genToken(){
        UserLoginDto userLoginDto = new UserLoginDto();
        long startTime = System.currentTimeMillis();
        userLoginDto.setLoginTime(startTime);
        userLoginDto.setExpireTime(startTime + timeout);
        String uuidToken = UUID.randomUUID().toString();
        userLoginDto.setToken(uuidToken);
        setUserAgent(userLoginDto);

        //指定签名的时候使用的签名算法。
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long thisTimeout = System.currentTimeMillis();
        Date createTokenDate = new Date(thisTimeout);
        //创建payload的私有声明
        Map<String, Object> claims = new HashMap<>(1);
        //参数信息
        claims.put(uId, uuidToken);

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder jwtBuilder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(createTokenDate)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject("blog")
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(algorithm, secret);
        /*
            if(timeout > 0){
                long expMillis = thisTimeout + thisTimeout
                Date exp = new Date(expMillis)
                //设置过期时间
                jwtBuilder.setExpiration(exp)
            }
        */
        redisCacheUtil.setValue(tokenKey(uuidToken),userLoginDto,timeout, TimeUnit.MINUTES);
        return jwtBuilder.compact();
    }

    /**
     * 校验token并返回userKey
     * @param userToken 登录令牌
     * @return
     * @throws MalformedJwtException
     */
    public String checkTokenReturnUid(String userToken) throws MalformedJwtException {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(userToken).getBody();
        String userKeySuffix = body.get(uId, String.class);
        return CommonConstant.LOGIN_USER_KEY+userKeySuffix;
    }

    /**
     * 校验令牌  自动刷新 600000  10分钟
     * @param userLoginDto 登录的实体
     */
    public void verifyToken(UserLoginDto userLoginDto){
        Long loginTime = userLoginDto.getLoginTime();
        Long expireTime = userLoginDto.getExpireTime();
        if(expireTime - loginTime >= REF_TOKEN_TIME){
            refreshToken(userLoginDto);
        }
    }

    /**
     * 刷新token的过期时间
     * @param userLoginDto
     */
    public void refreshToken (UserLoginDto userLoginDto){
        String token = userLoginDto.getToken();
        long loginTime = System.currentTimeMillis();
        userLoginDto.setLoginTime(loginTime);
        userLoginDto.setExpireTime(loginTime + timeout);
        redisCacheUtil.setValue(tokenKey(token),userLoginDto,timeout,TimeUnit.MINUTES);
    }

    /**
     * 设置用户的浏览器标识
     * @param userLoginDto 登录的数据承载类
     */
    private void setUserAgent(UserLoginDto userLoginDto){
        //获取浏览器版本标识
        String userAgent = ServletUtil.getUserAgent();
        userLoginDto.setUserAgent(userAgent);
        UserAgent parseUserAgent = UserAgentUtil.parse(userAgent);
        //获取浏览器类型
        userLoginDto.setBrowserType(parseUserAgent.getBrowser().getName());
        //获取操作系统名称
        userLoginDto.setOs(parseUserAgent.getOs().getName());
        //获取ip
        String ipAddr = IpUtils.getIpAddr(ServletUtil.getRequest());
        userLoginDto.setIp(ipAddr);

        String address = "未知地域";
        IpAddressDto ipAddress = IpUtils.getIpAddress(ipAddr, appKey);
        if(ipAddress != null){
            //国家
            String country = ipAddress.getCountry();
            //省份
            String province = ipAddress.getProvince();
            //市区
            String city = ipAddress.getCity();
            //运营商
            String isp = ipAddress.getIsp();
            address = String.format("%s %s %s %s",country,province,city,isp);
        }
        userLoginDto.setAddress(address);
    }

    /**
     * 包装 userKey 与token前缀进行拼接
     * @param userKey 缓存中用户的key的后缀
     * @return 缓存key
     */
    private String tokenKey(String userKey) {
        return CommonConstant.LOGIN_USER_KEY+userKey;
    }

}
