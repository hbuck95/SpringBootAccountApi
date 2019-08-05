package com.bae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/test")
	public String test() {
		return "Hello world!";
	}

}
