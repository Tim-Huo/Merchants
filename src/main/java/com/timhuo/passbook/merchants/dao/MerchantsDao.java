package com.timhuo.passbook.merchants.dao;

import com.timhuo.passbook.merchants.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    /**
     * 根据id获取商户对象
     *
     * @param id 商户id
     * @return {@link Merchants}
     */
    Optional<Merchants> findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     *
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);
}
