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
import com.remotegroup.sales.BackOrderSale;
import com.remotegroup.sales.BackOrderSaleRepository;
import com.remotegroup.sales.InStoreSale;
import com.remotegroup.sales.InStoreSaleRepository;
import com.remotegroup.sales.OnlineSale;
import com.remotegroup.sales.OnlineSaleRepository;
import com.remotegroup.sales.Sale;
import com.remotegroup.sales.SaleRepository;
import com.remotegroup.sales.Store;
import com.remotegroup.sales.StoreRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ContactRepository cRepository, SupplierRepository sRepository, ProductRepository prRepository, PartRepository paRepository, BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, SaleRepository saRepository, StoreRepository stRepository) {

    return args -> {
    	Supplier s = new Supplier("Pear", "Wollongong");
      log.info("Preloading " + sRepository.save(s));
      log.info("Preloading " + cRepository.save(new Contact(s.getSupplierId(),"Jim Davis", "0408459354", "jim@email.com", "Executive")));
      Part pa1 = new Part(s.getSupplierId(), "Part1", "description", 5);
      Long[][] l = {
        {pa1.getId(), (long) 5}
      };
      paRepository.save(pa1);
      log.info("Preloading " + prRepository.save(new Product("Bike1", 4.50, "comment", l, 7)));
      log.info("Preloading " + stRepository.save(new Store("Store1", "Mike")));
      log.info("Preloading " + saRepository.save(new Sale((long)0, "Bike1", 2, "22-08-2022")));
      log.info("Preloading " + iRepository.save(new InStoreSale((long)0, "R-0")));
      log.info("Preloading " + oRepository.save(new OnlineSale("John", "Antarctica")));
      log.info("Preloading " + bRepository.save(new BackOrderSale("+61134564351")));
    };
  }
}