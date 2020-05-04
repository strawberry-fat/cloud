package com.ljx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PaymentMain8001
 * @Description TODO
 * @Author 李金星
 * @Date 2020/4/2818:08
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {
    /**
     * TODO
     * @param args
     * @return void
     * @throws
     * @date 2020/5/3 15:18
     * @author 李金星
     */
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
