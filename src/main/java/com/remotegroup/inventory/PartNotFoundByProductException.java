package com.remotegroup.inventory;

public class PartNotFoundByProductException extends RuntimeException {
	PartNotFoundByProductException(Long id){
		super("Could not find part with Product "+id);
	}
}