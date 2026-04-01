package com.simplilearn.estore.enduser.model;

public class Cart {
	
	Integer cartId;
		Integer productId;
		Integer userId;
		
		public Cart() {
			
		}

		public Integer getCartId() {
			return cartId;
		}

		public void setCartId(Integer cartId) {
			this.cartId = cartId;
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

