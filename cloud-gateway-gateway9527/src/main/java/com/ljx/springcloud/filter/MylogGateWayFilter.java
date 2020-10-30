package com.ljx.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局GatewFilter，需要实现GlobalFilter, Ordered 接口。
 * 功能：请求中必须带有参数uname，且参数不能为空。
 */
@Component
@Slf4j
public class MylogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**********进入全局过滤器*****");
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.info("**********用户名为null,非法用户,o(╥﹏╥)o*****");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        //调用过滤链中的下一个Filter。
        return chain.filter(exchange);
    }

    /**
     * 过滤器优先级，值越大则优先级越低
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
