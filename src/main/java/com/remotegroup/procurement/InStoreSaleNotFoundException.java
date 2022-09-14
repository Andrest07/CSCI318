package com.remotegroup.procurement;

public class InStoreSaleNotFoundException extends RuntimeException {
	InStoreSaleNotFoundException(Long id){
		super("Could not find InStoreSale "+id);
	}
}