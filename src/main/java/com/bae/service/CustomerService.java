package com.bae.service;

import java.util.List;

import com.bae.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer updateCustomer(Customer customer);

	public String deleteCustomer(long id);

	public Customer findCustomer(long id);

	public String createCustomer(Customer customer);

}
