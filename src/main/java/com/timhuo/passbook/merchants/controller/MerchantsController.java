package com.timhuo.passbook.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.timhuo.passbook.merchants.service.MerchantsService;
import com.timhuo.passbook.merchants.vo.CreateMerchantsRequestVO;
import com.timhuo.passbook.merchants.vo.PassTempateVO;
import com.timhuo.passbook.merchants.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 商户服务 Controller
 * @author: Tim_Huo
 * @created: 2020/10/08 10:01
 */
@RestController
@RequestMapping("/merchants")
@Slf4j
public class MerchantsController {

    /**
     * 商户服务接口
     */
    @Autowired
    private MerchantsService merchantsService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseVO createMerchants(@RequestBody CreateMerchantsRequestVO createMerchantsRequestVO) {
        log.info("CreateMerchants：{}", JSON.toJSON(createMerchantsRequestVO));
        return merchantsService.createMerchants(createMerchantsRequestVO);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseVO buildMerchantsInfo(@PathVariable Integer id) {
        log.info("buildMerchantsInfo：id={}", id);
        System.out.println(id);
        return merchantsService.buildMerchantsInfoById(id);
    }

    @PostMapping("/drop")
    @ResponseBody
    public ResponseVO dropPassTemplate(@RequestBody PassTempateVO passTempateVO) {
        log.info("dropPassTemplate：{}", JSON.toJSON(passTempateVO));
        return merchantsService.dropPassTemplate(passTempateVO);
    }
}
