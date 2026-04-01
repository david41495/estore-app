package com.simplilearn.estore.admin.model;

import java.util.Date;
import java.util.List;

public class Order {
	
	Integer orderId;
	Date orderDate;
	String orderStatus;
	List<OrderItem> products;
	Integer totalItems;
	Double itemsSubTotal;
	Double shipmentChargers;
	Double totalAmount;
	Integer paymentStatus;
	String paymentStatustitle;
	Integer paymentMethod;
	String paymentMethodTitle;
	Integer userId;
	
	public Order() {
		
	}
	
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public List<OrderItem> getProducts() {
		return products;
	}
	
	public void setProducts(List<OrderItem> products) {
		this.products = products;
	}
	
	public Integer getTotalItems() {
		return totalItems;
	}
	
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	
	public Double getItemsSubTotal() {
		return itemsSubTotal;
	}
	
	public void setItemsSubTotal(Double itemsSubTotal) {
		this.itemsSubTotal = itemsSubTotal;
	}
	
	public Double getShipmentChargers() {
		return shipmentChargers;
	}
	
	public void setShipmentChargers(Double shipmentChargers) {
		this.shipmentChargers = shipmentChargers;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getPaymentStatustitle() {
		return paymentStatustitle;
	}
	
	public void setPaymentStatustitle(String paymentStatustitle) {
		this.paymentStatustitle = paymentStatustitle;
	}
	
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getPaymentMethodTitle() {
		return paymentMethodTitle;
	}
	
	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
