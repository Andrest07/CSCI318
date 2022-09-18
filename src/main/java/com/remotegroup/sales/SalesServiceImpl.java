package com.remotegroup.sales;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService{


	private final SaleRepository repository;
	SalesServiceImpl(SaleRepository repository){
		this.repository = repository;
	}
	
	@Override
	public List<Sale> getSales() {
		  return repository.findAll();
	}

	@Override
	public Sale createSale(Sale s) {
		return repository.save(s);
	}

	@Override
	public Sale updateSale(Sale s, Long id) {
		return repository.findById(id)
		      	.map(Sale -> {
		            Sale.setItemId(s.getItemId());
		            Sale.setItemName(s.getItemName());
		            Sale.setQuantity(s.getQuantity());
		            Sale.setDataTime(s.getDataTime());
		        return repository.save(Sale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return repository.save(s);
		      });
	}

	@Override
	public Sale getSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new SaleNotFoundException(id);
		}
	}

	@Override
	public void deleteSale(Long id) {
		repository.deleteById(id);
	}
	
}
