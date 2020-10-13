package com.ljx.srpingcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
