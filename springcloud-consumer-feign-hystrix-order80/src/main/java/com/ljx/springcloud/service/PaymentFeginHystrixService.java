package com.ljx.springcloud.service;


import org.springframework.stereotype.Component;

/**
 * 对这个fegin客户端的fallback进行实现。
 */
@Component
public class PaymentFeginHystrixService implements PaymentService{
    @Override
    public String payment_Ok(int id) {
        return "PaymentFeginHystrixService: payment_Ok : o(╥﹏╥)o  ~~~";
    }

    @Override
    public String payment_TimeOut(int id) {
        return "PaymentFeginHystrixService: payment_TimeOut : o(╥﹏╥)o  ~~~";
    }
}
