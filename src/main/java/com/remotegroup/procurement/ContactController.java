package com.remotegroup.procurement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

@RestController
public class ContactController {

	private final ContactRepository repository;
	private final ContactModelAssembler assembler;
	
	ContactController(ContactRepository repository, ContactModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	  }

	//use case: looks up all contacts and transforms them into a REST collection resource.
	@GetMapping("/contacts")
	CollectionModel<EntityModel<Contact>> all() {

	List<EntityModel<Contact>> contacts = repository.findAll().stream() //
		.map(assembler::toModel) //
		.collect(Collectors.toList());

	return CollectionModel.of(contacts, linkTo(methodOn(ContactController.class).all()).withSelfRel());
	}
	
	//use case: create contact
	@PostMapping("/contact")
	Contact newContact(@RequestBody Contact newContact) {
		return repository.save(newContact);
	}
	
	//use case: update contact
	@PutMapping("/contact/{id}")
	Contact replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Contact -> {
			Contact.setSupplierId(newContact.getSupplierId());
			Contact.setName(newContact.getName());
			Contact.setPhone(newContact.getPhone());
			Contact.setEmail(newContact.getEmail());
			Contact.setPosition(newContact.getPosition());
        return repository.save(Contact);
      })
      	.orElseGet(() -> {
        	newContact.setId(id);
        	return repository.save(newContact);
      });
	}
	
	
	//use case: delete contact
	@DeleteMapping("/contact/{id}")
	void deleteContact(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get contact by id

	@GetMapping("/contact/{id}")
	EntityModel<Contact> one(@PathVariable Long id) {

	Contact contact = repository.findById(id) //
		.orElseThrow(() -> new ContactNotFoundException(id));

	return assembler.toModel(contact);
	}
}
