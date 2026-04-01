package com.simplilearn.estore.enduser.service;

import com.simplilearn.estore.admin.model.Order;
import com.simplilearn.estore.admin.model.User;

public interface IOrderService {
	
	public Boolean placeOrder(User user, Order order);

}
