package com.bae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bae.entity.Customer;
import com.bae.entity.SentCustomer;
import com.bae.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService service;
	private RestTemplate template;
	private JmsTemplate jmsTemplate;

	@Autowired
	public CustomerController(CustomerService service, RestTemplate template, JmsTemplate jmsTemplate) {
		this.service = service;
		this.template = template;
		this.jmsTemplate = jmsTemplate;
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

		ResponseEntity<String> accountNumber = template.exchange("http://localhost:8082/numgen", HttpMethod.GET, null,
				String.class);

		System.out.println(accountNumber.getBody());

		ResponseEntity<Integer> prize = template.exchange("http://localhost:8081/prizegen/" + accountNumber.getBody(),
				HttpMethod.GET, null, Integer.class);

		System.out.println(accountNumber.getBody());

		customer.setAccountNumber(accountNumber.getBody());
		customer.setPrize(prize.getBody());
		return service.createCustomer(customer);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") String id) {
		return service.deleteCustomer(id);
	}

	@GetMapping("/test")
	public String test() {
		return "Hello world!";
	}

	private void sendToQueue(Customer customer) {
		SentCustomer customerToStore = new SentCustomer(customer);
		jmsTemplate.convertAndSend("AccountQueue", customerToStore);
	}

}
