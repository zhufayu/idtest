/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.service;

import com.dmall.distributedidtest.bean.WmOrderEntity;
import com.dmall.distributedidtest.mapper.test01.WmOrderMapper;
import com.dmall.distributedidtest.mapper.test02.OrderMapper;
import com.dmall.distributedidtest.util.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WmOrderService {

    Logger logger = LoggerFactory.getLogger(WmOrderService.class);
    Time timeu = new Time();
    WmOrderEntity wmOrderEntity2 = new WmOrderEntity();

    @Autowired
    private WmOrderMapper wmOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void insertWmOrder(WmOrderEntity wmOrderEntity) {
        wmOrderEntity2 = timeu.util(wmOrderEntity);
        wmOrderMapper.insert(wmOrderEntity2);
    }
    @Transactional
    public int insertWmOrderList(List<WmOrderEntity> orderlist) {
        return wmOrderMapper.insertList(orderlist);
    }

    @Transactional
    public void insertOrder(WmOrderEntity wmOrderEntity) {
        wmOrderEntity2 = timeu.util(wmOrderEntity);
        orderMapper.insert(wmOrderEntity2);
    }
    @Transactional
    public int insertOrderList(List<WmOrderEntity> orderlist) {
        return orderMapper.insertList(orderlist);
    }

    @Transactional
    public void insertOrder2DB(WmOrderEntity wmOrderEntity) {
        wmOrderEntity2 = timeu.util(wmOrderEntity);
        wmOrderMapper.insert(wmOrderEntity2);
        orderMapper.insert(wmOrderEntity2);
    }
    @Transactional
    public int insertList2DB(List<WmOrderEntity> orderlist) {
        wmOrderMapper.insertList(orderlist);
        return orderMapper.insertList(orderlist);
    }

    @Transactional
    public synchronized List<WmOrderEntity> selectByOrderId(WmOrderEntity queryEntity) {
        List<WmOrderEntity> wmOrderEntity = wmOrderMapper.selectByOrderId(queryEntity);
        return wmOrderEntity;
    }
    @Transactional
    public void updatewmorder(WmOrderEntity wmOrderEntity) {
        wmOrderMapper.updateWmOrder(wmOrderEntity);
    }
    @Transactional
    public void delete(Long id) {
        wmOrderMapper.delete(id);
    }


    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public void insert(WmOrderEntity wmOrderEntity) {
        wmOrderEntity2 = timeu.util(wmOrderEntity);
        wmOrderMapper.insert(wmOrderEntity2);
    }

    public void insert$(WmOrderEntity wmOrderEntity) {

        wmOrderMapper.insert$(wmOrderEntity);
    }
    public List<WmOrderEntity> selectAllId() {
        List<WmOrderEntity> wmOrderEntityAllId = wmOrderMapper.selectAllId();
        return wmOrderEntityAllId;
    }
    public Timestamp created() {
        Timestamp created2 = wmOrderEntity2.getCreated();
        return created2;
    }
}
