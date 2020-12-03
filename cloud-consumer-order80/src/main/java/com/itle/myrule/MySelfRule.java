package com.itle.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author itle
 * @version 1.0
 * @date 2020/7/15
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){

        return new RandomRule(); //负载均衡定义为随机规则
    }
}
