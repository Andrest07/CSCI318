package com.remotegroup.procurement;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Supplier {
	private @Id @GeneratedValue Long id;
	String companyName;
	String base;
	
	Supplier(String i, String cN, String b){
		id = i;
		companyName = cN;
		base = b;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getBase() {
		return base;
	}
	
	public String getSupplierId() {
		return id;
	}
	
	public void setCompanyName(String newName) {
		this.companyName = newName;
	}
	
	public void setBase(String b) {
		this.base = b;
	}
	
	public void setId(string newId) {
		this.id = newId;
	}
}
