package com.itle.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author itle
 * @version 1.0
 * @date 2020/7/16
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }

}
