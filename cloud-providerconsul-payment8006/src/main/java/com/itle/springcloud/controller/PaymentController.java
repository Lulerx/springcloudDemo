package com.itle.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author itle
 * @version 1.0
 * @date 2020/7/15
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul(){

        return "springcloud with sonsul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

}
