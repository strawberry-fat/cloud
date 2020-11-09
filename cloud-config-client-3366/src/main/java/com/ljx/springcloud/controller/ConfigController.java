package com.ljx.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope：当配置中心的配置文件更新之后，手动发送一个post请求到
 *                  localhost:3355/actuator/refresh，
 *                  可以不重启服务就能重新加载新的配置文件
 *                  必须有@RefreshScope注解和actuator依赖。
 */
@RefreshScope
@RestController
public class ConfigController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "ServerPort: "+serverPort+"\t\n\n  configInfo: "+configInfo;
    }
}
