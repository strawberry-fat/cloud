package com.ljx.springcloud.controller;

import com.ljx.springcloud.entity.CommonResult;
import com.ljx.springcloud.entity.Payment;
import com.ljx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 支付表(Payment)表控制层
 *
 * @author makejava
 * @since 2020-05-02 11:10:54
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    /**
     * 服务发现
     */
    @Resource
    private DiscoveryClient discoveryClient;
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;
    /**
     * 配置文件中的端口号
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return new CommonResult(200, "查询数据库成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(400, "数据库没有该记录,serverPort:" + serverPort, payment);
        }
    }


    /**
     * 新增记录
     *
     * @param payment 需要新增的数据
     * @return 单条数据
     */
    @PostMapping("/add")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(400, "插入数据库失败,serverPort:" + serverPort, result);
        }

    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****element:" + element);
        }
        //根据服务名称获取服务实例的信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }

    @GetMapping("/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        }
        return "8001";
    }

}