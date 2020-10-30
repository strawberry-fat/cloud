package com.ljx.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用编码方式配置路由。
 */
@Configuration
public class GateConfig {

    /*
        创建一个路由，使得localhost:9527/news跳转到http://news.baidu.com/
     */
    @Bean   
    public RouteLocator consumerRouterLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("",r -> r.path("/news").uri("http://news.baidu.com/")).build();
        return routes.build();
    }
}
