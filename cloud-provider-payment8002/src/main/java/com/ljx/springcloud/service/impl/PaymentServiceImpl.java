package com.ljx.springcloud.service.impl;

import com.ljx.springcloud.entity.Payment;
import com.ljx.springcloud.dao.PaymentDao;
import com.ljx.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付表(Payment)表服务实现类
 *
 * @author makejava
 * @since 2020-05-02 11:10:53
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment getById(Long id) {
        return this.paymentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Payment> getAllByLimit(int offset, int limit) {
        return this.paymentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Payment payment) {
        return   this.paymentDao.insert(payment);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        this.paymentDao.update(payment);
        return this.getById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.paymentDao.deleteById(id) > 0;
    }
}