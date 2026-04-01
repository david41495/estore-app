package com.simplilearn.estore.admin.model;

import java.util.Date;

public class Shipment {

	Integer shipmentId;
	Integer shipmentStatus;
	String shipmentTitle;
	Date shipmentDate;
	String shipmentMethod;
	String shipmentCompany;
	
	public Shipment() {
		
	}
	
	
	public Integer getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	public Integer getShipmentStatus() {
		return shipmentStatus;
	}
	public void setShipmentStatus(Integer shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	public String getShipmentTitle() {
		return shipmentTitle;
	}
	public void setShipmentTitle(String shipmentTitle) {
		this.shipmentTitle = shipmentTitle;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getShipmentMethod() {
		return shipmentMethod;
	}
	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}
	public String getShipmentCompany() {
		return shipmentCompany;
	}
	public void setShipmentCompany(String shipmentCompany) {
		this.shipmentCompany = shipmentCompany;
	}
	
	
}
