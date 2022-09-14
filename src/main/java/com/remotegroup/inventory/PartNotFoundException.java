package com.remotegroup.inventory;

public class PartNotFoundException extends RuntimeException {
	PartNotFoundException(Long id){
		super("Could not find part "+id);
	}
}