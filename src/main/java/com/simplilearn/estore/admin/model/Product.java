package com.simplilearn.estore.admin.model;

import java.util.Date;
import java.util.List;

public class Product {
	
	Integer productId;
	String productTitle;
	String productDescription;
	String productCode;
	List<String> images;
	Integer thumbnailImage;
	Integer price;
	Date addedOn;
	Integer rating;
	
	public Product() {
		
	}

	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getProductTitle() {
		return productTitle;
	}
	
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public List<String> getImages() {
		return images;
	}
	
	public void setImages(List<String> images) {
		this.images = images;
	}
	
	public Integer getThumbnailImage() {
		return thumbnailImage;
	}
	
	public void setThumbnailImage(Integer thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Date getAddedOn() {
		return addedOn;
	}
	
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	

}
