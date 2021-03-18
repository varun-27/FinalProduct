package com.infy.Product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscribedproduct")
public class Subscribedproduct {
@Id
@Column(nullable = false,length=11)
@GeneratedValue(strategy = GenerationType.IDENTITY)
int subId;
@Column(nullable = false,length=11)
int buyerId;
@Column(nullable = false,length=11)
int prodId;
@Column(nullable = false,length=11)
int quantity;
public Subscribedproduct()
{
super();	
}
public Subscribedproduct(int subid, int buyerid,int prodid,int quantity)
{
	this();
	this.subId=subid;
	this.buyerId=buyerid;
	this.prodId=prodid;
	this.quantity=quantity;
}
public int getSubid() {
	return subId;
}
public void setSubid(int subid) {
	this.subId = subid;
}
public int getBuyerid() {
	return buyerId;
}
public void setBuyerid(int buyerid) {
	this.buyerId = buyerid;
}
public int getProdid() {
	return prodId;
}
public void setProdid(int prodid) {
	this.prodId = prodid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
}
