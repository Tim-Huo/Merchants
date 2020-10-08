package com.timhuo.passbook.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 创建商户响应对象
 * @author: Tim_Huo
 * @created: 2020/10/08 08:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponseVo {

    /**
     * 商户 id：创建失败则为-1
     */
    private Integer id;
}
