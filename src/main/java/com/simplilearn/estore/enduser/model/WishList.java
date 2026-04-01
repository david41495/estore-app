package com.simplilearn.estore.enduser.model;

public class WishList {

	Integer wishListId;
		Integer productId;
		Integer userId;
		
		public WishList() {
			
		}

		public Integer getWishListId() {
			return wishListId;
		}

		public void setWishListId(Integer wishListId) {
			this.wishListId = wishListId;
		}

		public Integer getProductId() {
			return productId;
		}

		public void setProductId(Integer productId) {
			this.productId = productId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		
}
