package com.autumn.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autumn.dto.IpAddressDto;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip操作工具类
 * @author huangfu
 */
@Slf4j
public class IpUtils {

    private final static String IP_SELECT_URL = "http://apis.juhe.cn/ip/ipNew?ip=%s&key=%s";

    public static IpAddressDto getIpAddress(String ip, String selectUrl) {
        String formatUrl = String.format(IP_SELECT_URL, ip, selectUrl);
        String ipDtoStr = HttpUtil.get(formatUrl);
        JSONObject jsonObject = JSON.parseObject(ipDtoStr);
        return jsonObject.getObject("result", IpAddressDto.class);
    }

    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        if ("127.0.0.1".equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("获取IP地址失败{},{}", e.getMessage(), e);
            }
        }
        return ip;
    }

    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.bcrypt("123456"));
        System.out.println(DigestUtil.bcryptCheck("123456", "$2a$10$gf2lvq9pwRqRqEhoJtBTYuclh7kQZ4aPoE3/4cA8eneWx4g6e1Ddm"));
    }
}