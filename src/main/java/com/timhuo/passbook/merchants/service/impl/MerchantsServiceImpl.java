package com.timhuo.passbook.merchants.service.impl;

import com.alibaba.fastjson.JSON;
import com.timhuo.passbook.merchants.constant.Constants;
import com.timhuo.passbook.merchants.constant.ErrorCode;
import com.timhuo.passbook.merchants.dao.MerchantsDao;
import com.timhuo.passbook.merchants.entity.Merchants;
import com.timhuo.passbook.merchants.service.MerchantsService;
import com.timhuo.passbook.merchants.vo.CreateMerchantsRequestVO;
import com.timhuo.passbook.merchants.vo.CreateMerchantsResponseVo;
import com.timhuo.passbook.merchants.vo.PassTempateVO;
import com.timhuo.passbook.merchants.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description: 商户服务接口实现
 * @author: Tim_Huo
 * @created: 2020/10/08 08:46
 */
@Service
@Slf4j
public class MerchantsServiceImpl implements MerchantsService {

    @Autowired
    private MerchantsDao merchantsDao;

    /**
     * kafka客户端
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 创建商户服务
     *
     * @auther: Tim_Huo
     * @param: createMerchantsRequestVO
     * @return: ResponseVO
     * @date: 2020/10/8 9:19 上午
     */
    @Override
    public ResponseVO createMerchants(CreateMerchantsRequestVO createMerchantsRequestVO) {
        ResponseVO responseVO = new ResponseVO();
        CreateMerchantsResponseVo createMerchantsResponseVo = new CreateMerchantsResponseVo();
        ErrorCode errorCodeEnums = createMerchantsRequestVO.validate(merchantsDao);
        responseVO.setErrorCode(errorCodeEnums.getCode());
        responseVO.setErrorMsg(errorCodeEnums.getDesc());

        if (errorCodeEnums != ErrorCode.SUCCESS) {
            createMerchantsResponseVo.setId(-1);
            return responseVO;
        }

        createMerchantsResponseVo.setId(merchantsDao.save(createMerchantsRequestVO.toMerchants()).getId());
        responseVO.setData(createMerchantsResponseVo);
        return responseVO;
    }

    /**
     * 根据id构建商户信息(根据id查询商户信息）
     *
     * @auther: Tim_Huo
     * @param: id
     * @return: ResponseVO
     * @date: 2020/10/8 9:19 上午
     */
    @Override
    public ResponseVO buildMerchantsInfoById(Integer id) {
        ResponseVO responseVO = new ResponseVO();
        Optional<Merchants> merchants = merchantsDao.findById(id);

        if (null == merchants) {
            responseVO.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            responseVO.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
            return responseVO;
        }

        responseVO.setErrorCode(ErrorCode.SUCCESS.getCode());
        responseVO.setErrorMsg(ErrorCode.SUCCESS.getDesc());
        responseVO.setData(merchants.get());
        return responseVO;
    }

    /**
     * 商户投放优惠券
     *
     * @auther: Tim_Huo
     * @param: passTempateVO
     * @return: ResponseVO
     * @date: 2020/10/8 9:20 上午
     */
    @Override
    public ResponseVO dropPassTemplate(PassTempateVO passTempateVO) {
        ResponseVO responseVO = new ResponseVO();
        ErrorCode errorCodeEnums = passTempateVO.validate(merchantsDao);
        responseVO.setErrorCode(errorCodeEnums.getCode());
        responseVO.setErrorMsg(errorCodeEnums.getDesc());

        if (errorCodeEnums != ErrorCode.SUCCESS) {
            return responseVO;
        }
        String passTemplate = JSON.toJSONString(passTempateVO);
        kafkaTemplate.send(
                Constants.TEMPLATE_TOPIC,
                Constants.TEMPLATE_TOPIC,
                passTemplate
        );
        log.info("DropPassTemplates：{}", passTempateVO);
        return responseVO;
    }
}
