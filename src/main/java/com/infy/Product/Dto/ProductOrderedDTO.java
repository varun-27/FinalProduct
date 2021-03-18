package com.infy.Product.Dto;

import java.math.BigDecimal;

public class ProductOrderedDTO {
	private int prodId;
	private int sellerId;
	private int quantity;
	private String status;
	private BigDecimal price;
	public int getProdid() {
		return prodId;
	}
	public void setProdid(int prodId) {
		this.prodId = prodId;
	}
	public int getSellerid() {
		return sellerId;
	}
	public void setSellerid(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
