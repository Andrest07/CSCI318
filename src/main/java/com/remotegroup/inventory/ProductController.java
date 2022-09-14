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
public class ProductController {
	
	private final ProductRepository repository;
	ProductController(ProductRepository repository){
		this.repository = repository;
	}
	
	//use case: get all products.
	@GetMapping("/products")
	List<Product> all() {
	  return repository.findAll();
	}
	
	//use case: create product
	@PostMapping("/product")
	Product newProduct(@RequestBody Product product) {
		return repository.save(product);
	}
	
	//use case: update product
	@PutMapping("/product/{id}")
	Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Product -> {
			Product.setName(newProduct.getName());
			Product.setPrice(newProduct.getPrice());
            Product.setComment(newProduct.getComment());
        return repository.save(Product);
      })
      	.orElseGet(() -> {
        	newProduct.setId(id);
        	return repository.save(newProduct);
      });
	}
	
	//use case: delete product
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get product by id
	@GetMapping("/product/{id}")
	Product getProductById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new ProductNotFoundException(id);
		}
	}
	
}