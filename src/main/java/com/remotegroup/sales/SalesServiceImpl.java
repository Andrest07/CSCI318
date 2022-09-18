package com.remotegroup.sales;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SalesServiceImpl implements SalesService{


	private final SaleRepository repository;
	private final RestTemplate restTemplate;
	
	SalesServiceImpl(SaleRepository repository, RestTemplateBuilder restTemplateBuilder){
		this.repository = repository;
		this.restTemplate = restTemplateBuilder.build();
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
	
	// Communicate with Inventory Service via REST
	@Override
	public boolean requestCheckInventory(Long itemId){
		String url = "localhost:8080/product/check/"+itemId;
		return this.restTemplate.getForObject(url, boolean.class);
	}
}
