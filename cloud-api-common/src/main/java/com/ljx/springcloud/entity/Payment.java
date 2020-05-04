package com.ljx.springcloud.entity;

import java.io.Serializable;

/**
 * 支付表(Payment)实体类
 *
 * @author makejava
 * @since 2020-05-02 11:10:49
 */
public class Payment implements Serializable {
    private static final long serialVersionUID = 203000067012205979L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 支付流水号
    */
    private String serial;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}