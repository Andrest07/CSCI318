package com.remotegroup.procurement;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private final ContactRepository repository;
	
	ContactController(ContactRepository repository){
		this.repository = repository;
	}
	
	//use case: create contact
	@PostMapping("/contact")
	Contact newContact(@RequestBody Contact newContact) {
		return repository.save(newContact);
	}
	
	//use case: update contact
	/*
	@PutMapping("/contact/{id}")
	Contact updateContact(@RequestBody newContact, @PathVariable Long id) {
		//awaiting integration
	}
	*/
	
	//use case: delete contact
	@DeleteMapping("/contact/{id}")
	void deleteContact(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get contact by id
	@GetMapping("/contact/{id}")
	Contact getContactById(@PathVariable Long id) {
		try {
			return repository.getReferenceById(id);
		}catch(Exception e) {
			throw new ContactNotFoundException(id);
		}
	}
}
