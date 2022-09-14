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
public class OnlineSaleController {
	
	private final OnlineSaleRepository repository;
	OnlineSaleController(OnlineSaleRepository repository){
		this.repository = repository;
	}
	
	//use case: get all OnlineSales.
	@GetMapping("/OnlineSales")
	List<OnlineSale> all() {
	  return repository.findAll();
	}
	
	//use case: create OnlineSale
	@PostMapping("/OnlineSale")
	OnlineSale newOnlineSale(@RequestBody OnlineSale OnlineSale) {
		return repository.save(OnlineSale);
	}
	

	//use case: update OnlineSale
	@PutMapping("/OnlineSale/{id}")
	OnlineSale replaceOnlineSale(@RequestBody OnlineSale newOnlineSale, @PathVariable Long id) {
		return repository.findById(id)
      	.map(OnlineSale -> {
			OnlineSale.setCustomerName(newOnlineSale.getCustomerName());
            OnlineSale.setAddress(newOnlineSale.getAddress());
        return repository.save(OnlineSale);
      })
      	.orElseGet(() -> {
        	newOnlineSale.setId(id);
        	return repository.save(newOnlineSale);
      });
	}
	
	//use case: delete OnlineSale
	@DeleteMapping("/OnlineSale/{id}")
	void deleteOnlineSale(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get OnlineSale by id
	@GetMapping("/OnlineSale/{id}")
	OnlineSale getOnlineSaleById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new OnlineSaleNotFoundException(id);
		}
	}
	
}