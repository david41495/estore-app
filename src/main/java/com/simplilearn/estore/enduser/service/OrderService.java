package com.simplilearn.estore.enduser.service;

import com.simplilearn.estore.admin.model.Order;
import com.simplilearn.estore.admin.model.User;
import com.simplilearn.estore.enduser.dao.IOrderDao;
import com.simplilearn.estore.enduser.dao.OrderDao;

public class OrderService implements IOrderService {
	
	IOrderDao dao;
	
	public OrderService() {
		dao = new OrderDao();
		
	}

	@Override
	public Boolean placeOrder(User user, Order order) {
		// TODO Auto-generated method stub
		
		return dao.placeOrder(user, order);
		
		//Some more validation methods before the order is placed goes here
	}

}
