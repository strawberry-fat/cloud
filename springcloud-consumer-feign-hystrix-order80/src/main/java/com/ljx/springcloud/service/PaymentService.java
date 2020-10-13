package com.ljx.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFeginHystrixService.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public  String payment_Ok(@PathVariable("id") int id );
    @GetMapping("/payment/hystrix/timeout/{id}")
    public  String payment_TimeOut(@PathVariable("id") int id );
}
