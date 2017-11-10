package com.wjl.order.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.mapper.TbOrderItemMapper;
import com.wjl.mapper.TbOrderMapper;
import com.wjl.mapper.TbOrderShippingMapper;
import com.wjl.order.dao.JedisClient;
import com.wjl.pojo.TbOrder;
import com.wjl.pojo.TbOrderItem;
import com.wjl.pojo.TbOrderShipping;

/**
 * 订单管理service
 * @author wujiale
 * 2017-11-04 上午12:00:41
 */
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
		//向订单表中插入记录
		//获得订单号
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		order.setOrderId(orderId + "");
		//补全pojo的属性
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		//0：未评价 1：已评价
		order.setBuyerRate(0);
		//向订单表插入数据
		tbOrderMapper.insert(order);
		//插入订单明细
		for (TbOrderItem tbOrderItem : orderItems) {
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + ""); 
			//向订单明细插入记录
			tbOrderItemMapper.insert(tbOrderItem);
		}
		//插入物流表
		//补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		tbOrderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}
}
