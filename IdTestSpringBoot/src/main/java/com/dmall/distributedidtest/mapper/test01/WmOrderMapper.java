/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.mapper.test01;


import com.dmall.distributedidtest.bean.WmOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WmOrderMapper {

    void insert(WmOrderEntity wmOrderEntity);
    List<WmOrderEntity> selectByOrderId(WmOrderEntity queryEntity);
    int insertList(List<WmOrderEntity> list);


    List<WmOrderEntity> selectAllId();

    void updateWmOrder(WmOrderEntity wmOrderEntity);

    void delete(Long id);

    void insert$(WmOrderEntity wmOrderEntity);

}