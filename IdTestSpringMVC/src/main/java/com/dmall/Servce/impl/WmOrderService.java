package com.dmall.Servce.impl;

import com.dmall.bean.WmOrderEntity;

import java.util.List;

public interface WmOrderService {
    public void insert(WmOrderEntity wmOrderEntity);
    public List<WmOrderEntity> selectId(Long orderid);
    int insertList(List<WmOrderEntity> list);
}
