package com.remotegroup.procurement;

import java.util.List;
import java.util.function.Function;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SaleController {
	
	private final SaleRepository repository;
	SaleController(SaleRepository repository){
		this.repository = repository;
	}
	
	//use case: get all sales.
	@GetMapping("/sales")
	List<Sale> all() {
	  return repository.findAll();
	}
	
	//use case: create sale
	@PostMapping("/sale")
	Sale newSale(@RequestBody Sale sale) {
		return repository.save(sale);
	}
	

	//use case: update sale
	@PutMapping("/sale/{id}")
	Sale replaceSale(@RequestBody Sale newSale, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Sale -> {
            Sale.setProductId(newSale.getProductId());
			Sale.setInStoreSaleId(newSale.getInStoreSaleId());
			Sale.setOnlineSaleId(newSale.getOnlineSaleId());
            Sale.setProductName(newSale.getProductName());
            Sale.setQuantity(newSale.getQuantity());
            Sale.setDataTime(newSale.getDataTime());
        return repository.save(Sale);
      })
      	.orElseGet(() -> {
        	newSale.setId(id);
        	return repository.save(newSale);
      });
	}
	
	//use case: delete sale
	@DeleteMapping("/sale/{id}")
	void deleteSale(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get sale by id
	@GetMapping("/sale/{id}")
	Sale getSaleById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new SaleNotFoundException(id);
		}
	}
	
}