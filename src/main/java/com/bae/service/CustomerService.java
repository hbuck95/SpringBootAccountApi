package com.bae.service;

import java.util.List;

import com.bae.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer updateCustomer(Customer customer);

	public String deleteCustomer(String id);

	public Customer findCustomer(String id);

	public String createCustomer(Customer customer);

}
