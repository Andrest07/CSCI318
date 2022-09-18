package com.remotegroup.sales;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SaleServiceImpl implements SaleService{


	private final SaleRepository saleRepository;
	private final InStoreSaleRepository inStoreSaleRepository;
	private final OnlineSaleRepository onlineSaleRepository;
	private final BackOrderSaleRepository backOrderSaleRepository;
	private final RestTemplate restTemplate;
	
	SaleServiceImpl(SaleRepository saleRepository, InStoreSaleRepository i, OnlineSaleRepository o, BackOrderSaleRepository b, RestTemplateBuilder restTemplateBuilder){
		this.saleRepository = saleRepository;
		this.inStoreSaleRepository = i;
		this.onlineSaleRepository = o;
		this.backOrderSaleRepository = b;
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public List<Sale> getSales() {
		  return saleRepository.findAll();
	}

	@Override
	public Sale createSale(Sale s) {
		return saleRepository.save(s);
	}

	@Override
	public Sale updateSale(Sale s, Long id) {
		return saleRepository.findById(id)
		      	.map(Sale -> {
		            Sale.setItemId(s.getItemId());
		            Sale.setItemName(s.getItemName());
		            Sale.setQuantity(s.getQuantity());
		            Sale.setDataTime(s.getDataTime());
		        return saleRepository.save(Sale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return saleRepository.save(s);
		      });
		
	}

	@Override
	public Sale getSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return saleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new SaleNotFoundException(id);
		}
	}

	@Override
	public void deleteSale(Long id) {
		saleRepository.deleteById(id);
	}
	
	// Communicate with Inventory Service via REST
	@Override
	public boolean requestCheckInventory(Long itemId){
		String url = "localhost:8080/product/check/"+itemId;
		return this.restTemplate.getForObject(url, boolean.class);
	}

	@Override
	public List<InStoreSale> getInStoreSales() {
		  return inStoreSaleRepository.findAll();
	}

	@Override
	public InStoreSale createSale(InStoreSale s) {
		return inStoreSaleRepository.save(s);	
	}

	@Override
	public InStoreSale updateSale(InStoreSale s, Long id) {
		return inStoreSaleRepository.findById(id)
		      	.map(InStoreSale -> {
		      		InStoreSale.setItemId(s.getItemId());
		      		InStoreSale.setItemName(s.getItemName());
		      		InStoreSale.setQuantity(s.getQuantity());
		      		InStoreSale.setDataTime(s.getDataTime());
					InStoreSale.setStoreId(s.getStoreId());
		            InStoreSale.setReceipt(s.getReceiptNo());
		        return inStoreSaleRepository.save(InStoreSale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return inStoreSaleRepository.save(s);
		      });
	}

	@Override
	public InStoreSale getInStoreSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return inStoreSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new InStoreSaleNotFoundException(id);
		}
	}

	@Override
	public void deleteInStoreSale(Long id) {
		inStoreSaleRepository.deleteById(id);
		
	}

	@Override
	public List<OnlineSale> getOnlineSales() {
		  return onlineSaleRepository.findAll();
	}

	@Override
	public OnlineSale createSale(OnlineSale s) {
		return onlineSaleRepository.save(s);
	}

	@Override
	public OnlineSale updateSale(OnlineSale s, Long id) {
		return onlineSaleRepository.findById(id)
		      	.map(OnlineSale -> {
					OnlineSale.setCustomerName(s.getCustomerName());
		            OnlineSale.setAddress(s.getAddress());
		        return onlineSaleRepository.save(OnlineSale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return onlineSaleRepository.save(s);
		      });
	}

	@Override
	public OnlineSale getOnlineSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return onlineSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new OnlineSaleNotFoundException(id);
		}
	}

	@Override
	public void deleteOnlineSale(Long id) {
		onlineSaleRepository.deleteById(id);
		
	}

	@Override
	public List<BackOrderSale> getBackOrderSales() {
		  return backOrderSaleRepository.findAll();
	}

	@Override
	public BackOrderSale createBackOrderSale(BackOrderSale s) {
		return backOrderSaleRepository.save(s);
	}

	@Override
	public void deleteBackOrderSale(Long id) {
		backOrderSaleRepository.deleteById(id);
		
	}

	@Override
	public BackOrderSale getBackOrderSale(Long id) throws BackOrderSaleNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return backOrderSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new BackOrderSaleNotFoundException();
		}
	}
}
