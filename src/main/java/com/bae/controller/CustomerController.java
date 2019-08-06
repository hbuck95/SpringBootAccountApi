package com.bae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.entity.Customer;
import com.bae.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public List<Customer> findAll() {
		return service.findAll();
	}

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}

	@PostMapping("/create")
	public String createCustomer(@RequestBody Customer customer) {
		return service.createCustomer(customer);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") long id) {
		return service.deleteCustomer(id);
	}

	@GetMapping("/test")
	public String test() {
		return "Hello world!";
	}

}
