package com.wjl.order.pojo;

import java.util.Date;
import java.util.List;

import com.wjl.pojo.TbOrder;
import com.wjl.pojo.TbOrderItem;
import com.wjl.pojo.TbOrderShipping;

/**
 * 下订单时接受参数用的pojo
 * @author wujiale
 * 2017-11-04 上午12:53:30
 */
public class Order extends TbOrder{
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public Order() {
		
	}
	public Order(String orderId, String payment, Integer paymentType, String postFee, Integer status, Date createTime,
			Date updateTime, Date paymentTime, Date consignTime, Date endTime, Date closeTime, String shippingName,
			String shippingCode, Long userId, String buyerMessage, String buyerNick, Integer buyerRate,
			List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
		super(orderId, payment, paymentType, postFee, status, createTime, updateTime, paymentTime, consignTime, endTime,
				closeTime, shippingName, shippingCode, userId, buyerMessage, buyerNick, buyerRate);
		this.orderItems = orderItems;
		this.orderShipping = orderShipping;
	}
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
