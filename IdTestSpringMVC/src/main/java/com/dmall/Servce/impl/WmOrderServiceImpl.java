package com.dmall.Servce.impl;

import com.dmall.Dao.WmOrderMapper;
import com.dmall.bean.WmOrderEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("wmOrderService")
public class WmOrderServiceImpl implements WmOrderService {
    @Resource
    private  WmOrderMapper wmOrderMapper;


    @Override
    public void insert(WmOrderEntity wmOrderEntity) {
       wmOrderMapper.insert(wmOrderEntity);

    }

    @Override
    public int insertList(List<WmOrderEntity> list){
      return wmOrderMapper.insertList(list);
    }

    @Override
    public synchronized List<WmOrderEntity> selectId(Long orderid) {
        List<WmOrderEntity> wmOrderEntity= wmOrderMapper.selectId(orderid);
        return wmOrderEntity;
    }
}
