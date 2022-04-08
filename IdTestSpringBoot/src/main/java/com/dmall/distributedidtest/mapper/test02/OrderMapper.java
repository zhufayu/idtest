/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.mapper.test02;


import com.dmall.distributedidtest.bean.WmOrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertList(List<WmOrderEntity> list);
    void insert(WmOrderEntity wmOrderEntity);
}