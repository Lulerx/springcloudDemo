package com.itle.springclou.service.impl;

import com.itle.springclou.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author itle
 * @version 1.0
 * @date 2020/7/23
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {

        return "PaymentFallbackService----- fall back paymentInfo_OK...o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {

        return "PaymentFallbackService----- fall back paymentInfo_TimeOut...o(╥﹏╥)o";
    }
}
