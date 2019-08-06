package com.bae.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "Customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String firstName;
	public String lastName;
	public String accountNumber;

	public Customer() {
		this.accountNumber = generateAccountNumber();
	}

	public Customer(long id, String firstName, String lastName, String accountNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s', accNo='%s']", id, firstName, lastName,
				accountNumber);
	}

	private String generateAccountNumber() {

		return RandomStringUtils.randomAlphanumeric(10);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}

}
