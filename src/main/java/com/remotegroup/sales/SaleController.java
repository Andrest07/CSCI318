package com.remotegroup.sales;

import java.util.List;
import java.util.function.Function;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.remotegroup.sales.BackOrderSale;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SaleController {
	
	@Autowired
	SaleService salesService;
	
	//use case: get all sales.
	@GetMapping("/sales")
	List<Sale> all() {
		return salesService.getSales();
	}
	
	//use case: create sale
	// CHECK INVENTORY
	@PostMapping("/sale")
	Sale newSale(@RequestBody Sale sale) {
		if(salesService.requestCheckInventory(sale.getItemId())) {
			return salesService.createSale(sale);	
		}else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Item is unavailable. Would you like to Back Order?","Warning", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				System.out.println("Phone number");
				Scanner sc = new Scanner(System.in);
				String pN = sc.nextLine();
				sc.close();
				BackOrderSale backOrderSale = new BackOrderSale(pN);
				return salesService.createBackOrderSale(backOrderSale);
			}else {
				return null;
			}
		}
	}
	

	//use case: update sale
	@PutMapping("/sale/{id}")
	Sale replaceSale(@RequestBody Sale newSale, @PathVariable Long id) {
		return salesService.updateSale(newSale, id);
	}
	
	//use case: delete sale
	@DeleteMapping("/sale/{id}")
	void deleteSale(@PathVariable Long id) {
		salesService.deleteSale(id);
	}
	
	//use case: get sale by id
	@GetMapping("/sale/{id}")
	Sale getSaleById(@PathVariable Long id) {
		return salesService.getSale(id);
	}
};