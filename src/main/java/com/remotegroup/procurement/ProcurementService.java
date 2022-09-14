package com.remotegroup.procurement;

import java.util.List;

public interface ProcurementService {
	
	//expose Supplier endpoints
	public abstract void deleteSupplier(Long id);
	public abstract List<Supplier> getSuppliers();
	public abstract Supplier createSupplier(Supplier s);
	public abstract Supplier updateSupplier(Supplier s);
	public abstract Supplier getSupplier(Long id);
	
	//expose Contact endpoints
	public abstract void deleteContact(Long id);
	public abstract List<Contact> getContacts();
	public abstract Contact createContact(Contact c);
	public abstract Contact updateContact (Contact c);
	public abstract Contact getContact(Long id);
}
