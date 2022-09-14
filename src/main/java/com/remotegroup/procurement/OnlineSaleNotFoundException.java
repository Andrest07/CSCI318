package com.remotegroup.procurement;

public class OnlineSaleNotFoundException extends RuntimeException {
	OnlineSaleNotFoundException(Long id){
		super("Could not find OnlineSale "+id);
	}
}