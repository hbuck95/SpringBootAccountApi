package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bae.entity.Customer;
import com.bae.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {
	private CustomerRepository repo;
	private RestTemplate template;

	@Autowired
	public CustomerServiceImplementation(CustomerRepository repo, RestTemplate template) {
		this.repo = repo;
		this.template = template;
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) repo.findAll();
	}

	@Override
	public String deleteCustomer(String id) {
		repo.deleteById(id);
		return "Customer deleted.";
	}

	@Override
	public Customer findCustomer(String id) {
		return (Customer) repo.findById(id).get();
	}

	@Override
	public String createCustomer(Customer customer) {
		// return repo.save(customer);
		// repo.save(customer);
		return "{\"Status\":\"Customer created\",\"Prize\":" + customer.getPrize() + "}";
		// return "Customer created.";
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customerToUpdate = (Customer) repo.findById(customer.id).get();
		customerToUpdate.setFirstName(customer.getFirstName());
		customerToUpdate.setLastName(customer.getLastName());
		repo.save(customerToUpdate);
		return customerToUpdate;
	}

}
