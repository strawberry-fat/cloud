package com.ljx.springcloud.controller;

import com.ljx.springcloud.entity.CommonResult;
import com.ljx.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author 李金星
 * @Date 2020/5/221:41
 * @Version 1.0
 **/
@RequestMapping("/consumer")
@RestController()
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/payment/add")
    public CommonResult add(Payment payment){
        log.info("123");
        log.info("789");
          return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
