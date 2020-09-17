package com.ljx.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon的负载均衡配置类
 *
 */

@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //指定为随机
        return  new RandomRule();
    }
}
