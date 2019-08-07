package com.bae.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.entity.Customer;
import com.bae.service.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController controller;

	@Mock
	private CustomerService service;

	private List<Customer> customerList;

	private static final Customer MOCK_CUSTOMER_A = new Customer("1", "Fred", "Derf", "abc123");
	private static final Customer UPDATED_MOCK_CUSTOMER_A = new Customer("1", "Mike", "Truk", "abc123");
	private static final Customer MOCK_CUSTOMER_B = new Customer("2", "Bobson", "Dugnutt", "def456");
	private static final String MOCK_CREATE_CUSTOMER_RESPONSE = "Customer created.";
	private static final String MOCK_DELETE_CUSTOMER_RESPONSE = "Customer deleted.";

	@Before
	public void setup() {
		customerList = new ArrayList<>();
	}

	@Test
	public void createCustomerTest() {
		Mockito.when(service.createCustomer(MOCK_CUSTOMER_A)).thenReturn(MOCK_CREATE_CUSTOMER_RESPONSE);
		String reply = controller.createCustomer(MOCK_CUSTOMER_A);
		assertEquals(MOCK_CREATE_CUSTOMER_RESPONSE, reply);
		Mockito.verify(service).createCustomer(MOCK_CUSTOMER_A);
	}

}
