package com.timhuo.passbook.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 返回通用类
 * @author: Tim_Huo
 * @created: 2020/10/08 08:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {

    /**
     * 错误码。正确返回 0
     */
    private Integer errorCode;

    /**
     * 错误信息，正确返回SUCCESS
     */
    private String errorMsg;

    /**
     * 返回值对象
     */
    private Object data;

    /**
     * 正确的响应构造函数
     *
     * @param data 返回值对象
     */
    public ResponseVO(Object data) {
        this.data = data;
    }
}
