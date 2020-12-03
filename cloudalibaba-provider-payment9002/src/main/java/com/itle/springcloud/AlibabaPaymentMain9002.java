package com.itle.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author itle
 * @version 1.0
 * @date 2020/8/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentMain9002 {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentMain9002.class, args);
    }

}
