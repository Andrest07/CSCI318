package com.remotegroup.sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BackOrderSale extends Sale {
	private @Id @GeneratedValue Long id;
	String phoneNumber;
	
	BackOrderSale(String p){
		phoneNumber = p;
	}
	
	String getPhoneNumber() {
		return phoneNumber;
	}
	
	void setPhoneNumber(String p){
		this.phoneNumber = p;
	}
	
}
