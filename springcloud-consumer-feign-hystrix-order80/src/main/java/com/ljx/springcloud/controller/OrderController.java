package com.ljx.springcloud.controller;

import com.ljx.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "fallback_default")
public class OrderController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public  String payment_Ok(@PathVariable("id") int id ){
        return paymentService.payment_Ok(id);
    }

    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public  String payment_TimeOut(@PathVariable("id") int id ){
        return paymentService.payment_TimeOut(id);
    }

    public String payment_TimeOutHandler(@PathVariable("id") int id){
        return "线程池: "+ Thread.currentThread().getName()+",  当前消费端系统繁忙请重试  o(╥﹏╥)o" ;
    }

    public String fallback_default(@PathVariable("id") int id){
        return "fallback_default,  当前消费端系统繁忙请重试  o(╥﹏╥)o" ;
    }
}
