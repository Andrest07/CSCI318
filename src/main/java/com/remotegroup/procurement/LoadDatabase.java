package com.remotegroup.procurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(SupplierRepository sRepository) {

    return args -> {
      log.info("Preloading " + sRepository.save(new Supplier("Pear", "Wollongong")));
    };
  }

  @Bean
  CommandLineRunner initDatabase(ContactRepository cRepository) {

    return args -> {
      //NEED to get supplierID somehow
      log.info("Preloading " + cRepository.save(new Contact(0L, "Jim Davis", "0408459354", "jim@email.com", "Executive")));
    };
  }
}