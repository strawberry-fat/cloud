package com.ljx.springcloud.service;

import com.ljx.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("cloud-payment-service")
public interface PaymentFeginService {
    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String timeout();
}
