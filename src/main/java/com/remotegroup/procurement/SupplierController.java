package com.remotegroup.procurement;

import java.util.function.Function;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {
	
	private final SupplierRepository repository;
	SupplierController(SupplierRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/suppliers")
	List<Supplier> all() {
	  return repository.findAll();
	}

	//use case: create supplier
	@PostMapping("/supplier")
	Supplier newSupplier(@RequestBody Supplier supplier) {
		return repository.save(supplier);
	}
	
	//use case: update supplier
	@PutMapping("/supplier/{id}")
	Supplier replaceSupplier(@RequestBody Supplier newSupplier, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Supplier -> {
			Supplier.setCompanyName(newSupplier.getCompanyName());
			Supplier.setBase(newSupplier.getBase());
        return repository.save(Supplier);
      })
      	.orElseGet(() -> {
        	newSupplier.setId(id);
        	return repository.save(newSupplier);
      });
	}
	
	//use case: delete supplier
	@DeleteMapping("/supplier/{id}")
	void deleteSupplier(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get supplier by id
	@GetMapping("/supplier/{id}")
	Supplier getSupplierById(@PathVariable Long id) {
		try {
			return repository.getReferenceById(id);
		}catch(Exception e) {
			throw new SupplierNotFoundException(id);
		}
	}
	
}
