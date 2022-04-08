package com.dmall.Dao;


import com.dmall.bean.WmOrderEntity;

import java.util.List;

public interface WmOrderMapper {


	void insert(WmOrderEntity wmOrderEntity);


	List<WmOrderEntity> selectId(Long orderid);

	int insertList(List<WmOrderEntity> list);

}