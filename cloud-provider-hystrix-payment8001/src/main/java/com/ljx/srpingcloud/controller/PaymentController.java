package com.ljx.srpingcloud.controller;

import com.ljx.srpingcloud.service.PaymenyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment/hystrix")
public class PaymentController {
    @Resource
    private PaymenyService paymenyService;

    @Value("server.port")
    private String serverPort;

    @GetMapping("/ok/{id}")
    public  String payment_Ok(@PathVariable("id") int id ){
        String result = paymenyService.paymentInfo_Ok(id);
        log.info("payment_ok: result:"+result);
        return  result;
    }

    @GetMapping("/timeout/{id}")
    public  String payment_TimeOut(@PathVariable("id") int id ){
        String result = paymenyService.paymentInfo_TimeOut(id);
        log.info("payment_TimeOut: result:"+result);
        return  result;
    }

    //===服务熔断===
    @GetMapping("/circuit/{id}")
    public  String paymentCircuitBreaker(@PathVariable("id") int id ){
        String result = paymenyService.paymentCircuitBreaker(id);
        log.info("payment_TimeOut: result:"+result);
        return  result;
    }
}
