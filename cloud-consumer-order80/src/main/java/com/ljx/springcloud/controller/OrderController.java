package com.ljx.springcloud.controller;

import com.ljx.springcloud.entity.CommonResult;
import com.ljx.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    /*
     * 服务名称
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    /*
     * RestTemplate调用服务
     */
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/payment/add")
    public CommonResult<Payment> add(Payment payment){
        log.info("123");
        log.info("789");
          return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }

    @PostMapping("/payment/post")
    public CommonResult<Payment> add2(@RequestBody Payment payment){
        ResponseEntity<Payment> paymentResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/add", payment, Payment.class);
        if(paymentResponseEntity.getStatusCode().is2xxSuccessful()){
            return new CommonResult<> (200,"添加成功",payment);
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        //返回对象为json，是响应体重数据转换的对象
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntityById(@PathVariable("id") Long id){
        //返回对象为ResponseEntity，包含了响应的一些重要信息。比如响应头，响应状态码，响应体等。
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
                if(entity.getStatusCode().is2xxSuccessful()){
                    return entity.getBody();
                }else {
                    return new CommonResult<>(444,"操作失败");
                }
    }
}
