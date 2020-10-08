package com.timhuo.passbook.merchants.service;

import com.alibaba.fastjson.JSON;
import com.timhuo.passbook.merchants.vo.CreateMerchantsRequestVO;
import com.timhuo.passbook.merchants.vo.PassTempateVO;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description: MerchantsServiceImplTest
 * @author: Tim_Huo
 * @created: 2020/10/08 08:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceImplTest {

    @Autowired
    private MerchantsService merchantsService;

    @Test
    @Transactional
    public void createMerchants() {
        CreateMerchantsRequestVO createMerchantsRequestVO = new CreateMerchantsRequestVO();
        createMerchantsRequestVO.setName("支付宝");
        createMerchantsRequestVO.setLogoUrl("www.imooc.com");
        createMerchantsRequestVO.setBusinessLicenseUrl("www.imooc.com");
        createMerchantsRequestVO.setPhone("1234567981");
        createMerchantsRequestVO.setAddress("北京市");

        System.out.println(JSON.toJSON(merchantsService.createMerchants(createMerchantsRequestVO)));
    }

    @Test
    public void buildMerchantsInfoById() {
        System.out.println(JSON.toJSON(merchantsService.buildMerchantsInfoById(6)));
    }

    @Test
    public void dropPassTemplate() {
        PassTempateVO passTempateVO = new PassTempateVO();
        passTempateVO.setId(2);
        passTempateVO.setTitle("tim");
        passTempateVO.setSummary("简介：tim");
        passTempateVO.setDesc("详情：tim");
        passTempateVO.setLimit(10000L);
        passTempateVO.setHasToKen(false);
        passTempateVO.setBackground(2);
        passTempateVO.setStart(new Date());
        passTempateVO.setEnd(DateUtils.addDays(new Date(), 10));
        System.out.println(JSON.toJSON(merchantsService.dropPassTemplate(passTempateVO)));
    }

}
