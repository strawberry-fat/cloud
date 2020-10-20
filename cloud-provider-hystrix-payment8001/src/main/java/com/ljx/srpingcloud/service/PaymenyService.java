package com.ljx.srpingcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymenyService {

    public String paymentInfo_Ok(Integer id){
            return "线程池: "+ Thread.currentThread().getName()+",  paymentInfo_Ok: "+id;
        }

    /**
     * Hystrix设置超时时间为3s。
     * 故意超时，测试降级.
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")})
    public String paymentInfo_TimeOut(Integer id){
        //故意超时，或者异常，都会由fallback处理
        int time = 5;
//        int age = 10/0;
        try{TimeUnit.SECONDS.sleep(time);}catch (Exception e){}
        return "线程池: "+ Thread.currentThread().getName()+",  paymentInfo_Timeout: "+id+", 休眠: "+time +"秒";
    }

    public String payment_TimeOutHandler(Integer id){
        return "线程池: "+ Thread.currentThread().getName()+",  当前系统繁忙请重试  o(╥﹏╥)o" ;
    }

    //=========服务熔断=============
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),    //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id不能为负数===========");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号为："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id: "+id+"不能为负数，请重新尝试=====";
    }
}
