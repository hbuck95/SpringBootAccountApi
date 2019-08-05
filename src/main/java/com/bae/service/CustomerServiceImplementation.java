package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.entity.Customer;
import com.bae.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {
	private CustomerRepository repo;

	@Autowired
	public CustomerServiceImplementation(CustomerRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) repo.findAll();
	}

}
