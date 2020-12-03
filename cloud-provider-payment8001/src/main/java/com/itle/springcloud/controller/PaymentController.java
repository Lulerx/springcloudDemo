package com.itle.springcloud.controller;

import com.itle.springcloud.entities.CommonResult;
import com.itle.springcloud.entities.Payment;
import com.itle.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/7/13
 */
@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 新增
     * postman http://localhost:8001/payment/create?serial=atguigu002
     *
     * @param payment
     * @return
     */
    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, payment);
        }
        return new CommonResult(444, "插入数据库失败", null);
    }


    /**
     * 查询
     * http://localhost:8001/payment/get/20
     *
     * @param id
     * @return
     */
    @GetMapping(value = "payment/get/{id}")
    @ResponseBody
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
    }



    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost()
            + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }



    @GetMapping("/payment/lb")
    public String getPaymentLB(){

        return serverPort;
    }

}
