package com.wjl.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.ExceptionUtil;
import com.wjl.order.pojo.Order;
import com.wjl.order.service.OrderService;

/**
 * 订单管理Controller
 * @author wujiale
 * 2017-11-04 上午12:48:37
 */
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order){
		try {
			TaotaoResult taotaoResult = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return taotaoResult;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
