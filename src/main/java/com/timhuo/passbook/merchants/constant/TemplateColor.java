package com.timhuo.passbook.merchants.constant;

import org.apache.kafka.common.protocol.types.Field;

/**
 * @description: 描述优惠券背景颜色
 * @author: Tim_Huo
 * @created: 2020/10/08 05:48
 */
public enum TemplateColor {

    RED(1, "红色"),
    GREEN(2, "绿色"),
    BLUE(3, "蓝色");

    /**
     * 颜色代码
     */
    private Integer code;

    /**
     * 颜色描述
     */
    private String color;

    TemplateColor(Integer code, String color) {
        this.code = code;
        this.color = color;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getColor() {
        return this.color;
    }
}
