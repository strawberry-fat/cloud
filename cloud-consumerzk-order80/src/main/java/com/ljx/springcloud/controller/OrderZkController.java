package com.ljx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 李金星
 * @Date 2020/5/10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderZkController {
    /**
     * 调用服务地址
     */
    public static final String INVOKE_URL="http://cloud-payment-service";
    /**
     * RestTemplate
     */
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
