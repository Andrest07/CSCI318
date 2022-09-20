package com.remotegroup.sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BackOrderSale extends Sale {
	private @Id @GeneratedValue Long id;
	String phoneNumber;
	
	public BackOrderSale(String p){
		phoneNumber = p;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String p){
		this.phoneNumber = p;
	}
	
}
