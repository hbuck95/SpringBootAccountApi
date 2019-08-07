package com.bae.entity;

public class SentCustomer {

	public String id;

	public String firstName;
	public String lastName;
	public String accountNumber;
	public int prize;

	public SentCustomer() {
	}

	public SentCustomer(Customer c) {
		this.id = c.getId();
		this.firstName = c.getFirstName();
		this.lastName = c.getLastName();
		this.accountNumber = c.getAccountNumber();
		this.prize = c.getPrize();
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s', accountNumber='%s', prize='%s']", id,
				firstName, lastName, accountNumber, prize);
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

	public String getId() {
		return id;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

}
