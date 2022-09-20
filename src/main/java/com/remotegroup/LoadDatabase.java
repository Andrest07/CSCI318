package com.remotegroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.inventory.Part;
import com.remotegroup.inventory.PartRepository;
import com.remotegroup.inventory.Product;
import com.remotegroup.inventory.ProductRepository;
import com.remotegroup.procurement.Contact;
import com.remotegroup.procurement.ContactRepository;
import com.remotegroup.procurement.Supplier;
import com.remotegroup.procurement.SupplierRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ContactRepository cRepository, SupplierRepository sRepository, ProductRepository prRepository, PartRepository paRepository) {

    return args -> {
    	Supplier s = new Supplier("Pear", "Wollongong");
      log.info("Preloading " + sRepository.save(s));
      log.info("Preloading " + cRepository.save(new Contact(s.getSupplierId(),"Jim Davis", "0408459354", "jim@email.com", "Executive")));
      Part pa1 = new Part(s.getSupplierId(), "name", "description", 5);
      Long[][] l = { 
        {pa1.getId(), (long) 5}
      };
      log.info("Preloading " + prRepository.save(new Product("name", 4.50, "comment", l, 7)));
      
    };
  }
}