package com.remotegroup.sales;

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
public class InStoreSaleController {
	
	private final InStoreSaleRepository repository;
	InStoreSaleController(InStoreSaleRepository repository){
		this.repository = repository;
	}
	
	//use case: get all InStoreSales.
	@GetMapping("/InStoreSales")
	List<InStoreSale> all() {
	  return repository.findAll();
	}
	
	//use case: create InStoreSale
	@PostMapping("/InStoreSale")
	InStoreSale newInStoreSale(@RequestBody InStoreSale InStoreSale) {
		return repository.save(InStoreSale);
	}
	

	//use case: update InStoreSale
	@PutMapping("/InStoreSale/{id}")
	InStoreSale replaceInStoreSale(@RequestBody InStoreSale newInStoreSale, @PathVariable Long id) {
		return repository.findById(id)
      	.map(InStoreSale -> {
			InStoreSale.setStoreId(newInStoreSale.getStoreId());
            InStoreSale.setReceipt(newInStoreSale.getReceipt());
        return repository.save(InStoreSale);
      })
      	.orElseGet(() -> {
        	newInStoreSale.setId(id);
        	return repository.save(newInStoreSale);
      });
	}
	
	//use case: delete InStoreSale
	@DeleteMapping("/InStoreSale/{id}")
	void deleteInStoreSale(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get InStoreSale by id
	@GetMapping("/InStoreSale/{id}")
	InStoreSale getInStoreSaleById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new InStoreSaleNotFoundException(id);
		}
	}
	
}
