package com.itle.springcloud.service;


import com.itle.springcloud.entities.Payment;

/**
 * @author itle
 * @date 2020/7/13 17:22
 */
public interface PaymentService {
    /**
     * 新增
     *
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
