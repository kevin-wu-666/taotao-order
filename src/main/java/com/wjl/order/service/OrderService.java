package com.wjl.order.service;

import java.util.List;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.pojo.TbOrder;
import com.wjl.pojo.TbOrderItem;
import com.wjl.pojo.TbOrderShipping;

public interface OrderService {
	TaotaoResult createOrder(TbOrder order,List<TbOrderItem> orderItems,TbOrderShipping orderShipping);
}
