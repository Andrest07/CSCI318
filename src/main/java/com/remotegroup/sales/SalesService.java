package com.remotegroup.sales;

import java.util.List;

public interface SalesService {
	
	//expose Sale endpoints
	public abstract List<Sale> getSales();
	public abstract Sale createSale(Sale s);
	public abstract Sale updateSale(Sale s, Long id);
	public abstract Sale getSale(Long id);
	public abstract void deleteSale(Long id);
	
}
