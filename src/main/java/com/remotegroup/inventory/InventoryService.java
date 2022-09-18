package com.remotegroup.inventory;

import java.util.List;

public interface InventoryService {
	public abstract List<Product> getProducts();
	public abstract Product createProduct(Product p);
	public abstract Product updateProduct(Product p, Long id);
	public abstract void deleteProduct(Long id);
	public abstract Product getProduct(Long id);
	
	public abstract List<Part> getParts();
	public abstract Part createPart(Part p);
	public abstract Part updatePart(Part p, Long id);
	public abstract void deletePart(Long id);
	public abstract Part getPart(Long id);
	public abstract Long getPartSupplier(Long id);
	
	public abstract boolean checkInventory(Long itemId);
}
