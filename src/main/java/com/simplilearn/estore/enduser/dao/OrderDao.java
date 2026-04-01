package com.simplilearn.estore.enduser.dao;

import java.util.HashMap;
import java.util.Map;

import com.simplilearn.estore.admin.model.Order;
import com.simplilearn.estore.admin.model.User;

public class OrderDao implements IOrderDao {
	
	Map<Integer, User>userEntry;
	Map<Integer, Order>orderEntry;
	
	
	public OrderDao() {
		
		userEntry = new HashMap<Integer, User>();
		orderEntry = new HashMap<Integer, Order>();
		
	}


	@Override
	public Boolean placeOrder(User user, Order order) {
		
		return true;
	}


	@Override
	public Order getOrderDetails(int orderId) {
		Order order = new Order();
		// TODO Auto-generated method stub
		
		return order;
	}
	
	

}
