package com.remotegroup.procurement;

public class SaleNotFoundException extends RuntimeException {
	SaleNotFoundException(Long id){
		super("Could not find sale "+id);
	}
}