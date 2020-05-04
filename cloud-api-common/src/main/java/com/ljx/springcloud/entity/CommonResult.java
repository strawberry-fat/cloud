package com.ljx.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResult
 * @Description 封装响应信息
 * @Author 李金星
 * @Date 2020/5/220:39
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T> {
    private Integer code;
    private String message;
    private T body;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
