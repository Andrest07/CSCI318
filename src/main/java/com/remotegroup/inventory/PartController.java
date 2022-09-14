package com.remotegroup.inventory;

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
public class PartController {
	
	private final PartRepository repository;
	PartController(PartRepository repository){
		this.repository = repository;
	}
	
	//use case: get all parts.
	@GetMapping("/parts")
	List<Part> all() {
	  return repository.findAll();
	}
	
	//use case: create part
	@PostMapping("/part")
	Part newPart(@RequestBody Part part) {
		return repository.save(part);
	}
	

	//use case: update part
	@PutMapping("/part/{id}")
	Part replacePart(@RequestBody Part newPart, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Part -> {
			Part.setSupplierId(newPart.getSupplierId());
			Part.setProductId(newPart.getProductId());
            Part.setName(newPart.getName());
            Part.setDescription(newPart.getDescription());
        return repository.save(Part);
      })
      	.orElseGet(() -> {
        	newPart.setId(id);
        	return repository.save(newPart);
      });
	}
	
	//use case: delete part
	@DeleteMapping("/part/{id}")
	void deletePart(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get part by id
	@GetMapping("/part/{id}")
	Part getPartById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new PartNotFoundException(id);
		}
	}
	
}
