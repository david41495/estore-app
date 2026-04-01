package com.simplilearn.estore.enduser.dao;

import com.simplilearn.estore.admin.model.Order;
import com.simplilearn.estore.admin.model.User;

public interface IOrderDao {
	
	public Boolean placeOrder(User user, Order order);
	public Order getOrderDetails(int orderId);

}
