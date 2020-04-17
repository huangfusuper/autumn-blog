package com.yunye.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ip地址映射
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpAddressDto implements Serializable {
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 市区
     */
    private String city;
    /**
     * 运营商
     */
    private String isp;

}
