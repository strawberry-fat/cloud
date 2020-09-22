package com.ljx.springcloud.controller;

import com.ljx.springcloud.entity.CommonResult;
import com.ljx.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class PaymentFeginController {
    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        return paymentFeginService.getById(id);
    }

    @GetMapping("timeout")
    public String timeout(){
       return paymentFeginService.timeout();
    }
}
