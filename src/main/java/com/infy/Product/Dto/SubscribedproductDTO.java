package com.infy.Product.Dto;

import com.infy.Product.entity.Subscribedproduct;

public class SubscribedproductDTO {
	int subId;
	int buyerId;
	int prodId;
	int quantity;
	public SubscribedproductDTO()
	{
	super();	
	}
	public SubscribedproductDTO(int subid,int buyerid,int prodid,int quantity) {
		this();
		this.subId=subid;
		this.buyerId=buyerid;
		this.prodId=prodid;
		this.quantity=quantity;
	}
	public int getSubid() {
		return subId;
	}
	public void setSubid(int subId) {
		this.subId = subId;
	}
	public int getBuyerid() {
		return buyerId;
	}
	public void setBuyerid(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getProdid() {
		return prodId;
	}
	public void setProdid(int prodId) {
		this.prodId = prodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public static SubscribedproductDTO valueOf(Subscribedproduct subscribedproduct)
	{
		SubscribedproductDTO subscribedproductDTO = new SubscribedproductDTO();
		subscribedproductDTO.setBuyerid(subscribedproduct.getBuyerid());
		subscribedproductDTO.setProdid(subscribedproduct.getProdid());
		subscribedproductDTO.setQuantity(subscribedproduct.getQuantity());
		subscribedproductDTO.setSubid(subscribedproduct.getSubid());
		return subscribedproductDTO;
	}
	
	public Subscribedproduct createEntity()
	{
		Subscribedproduct sp= new Subscribedproduct();
		sp.setBuyerid(this.getBuyerid());
		sp.setProdid(this.getProdid());
		sp.setQuantity(this.getQuantity());
		sp.setSubid(this.getSubid());
		return sp;
	}
}
