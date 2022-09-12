package com.remotegroup.procurement;

public class PartNotFoundException extends RuntimeException {
	PartNotFoundException(Long id){
		super("Could not find part "+id);
	}
}