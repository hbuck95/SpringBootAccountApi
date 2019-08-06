package com.bae.service;

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
import com.bae.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImplementation service;

	@Mock
	private CustomerRepository repo;

	private List<Customer> customerList;

	private static final Customer MOCK_CUSTOMER_A = new Customer(1L, "Fred", "Derf", "abc123");
	private static final Customer UPDATED_MOCK_CUSTOMER_A = new Customer(1L, "Mike", "Truk", "abc123");
	private static final Customer MOCK_CUSTOMER_B = new Customer(2L, "Bobson", "Dugnutt", "def456");
	private static final String MOCK_CREATE_CUSTOMER_RESPONSE = "Customer created.";
	private static final String MOCK_DELETE_CUSTOMER_RESPONSE = "Customer deleted.";

	@Before
	public void setup() {
		customerList = new ArrayList<>();
	}

	@Test
	public void createCustomerTest() {
		String reply = service.createCustomer(MOCK_CUSTOMER_A);
		Mockito.when(repo.save(MOCK_CUSTOMER_A)).thenReturn(MOCK_CUSTOMER_A);
		assertEquals(MOCK_CREATE_CUSTOMER_RESPONSE, reply);
		Mockito.verify(repo).save(MOCK_CUSTOMER_A);
	}

	@Test
	public void updateCustomerTest() {
		customerList.add(MOCK_CUSTOMER_A);

		System.out.println(customerList.get(0));

		Mockito.when(repo.findById(MOCK_CUSTOMER_A.id))
				.thenReturn((customerList.stream().filter(x -> x.id == MOCK_CUSTOMER_A.id).findFirst()));

		Customer updatedCustomer = service.updateCustomer(UPDATED_MOCK_CUSTOMER_A);

		System.out.println(customerList.get(0));

		assertEquals(updatedCustomer, service.updateCustomer(MOCK_CUSTOMER_A));
		assertEquals(UPDATED_MOCK_CUSTOMER_A.getFirstName(), customerList.get(0).getFirstName());

	}

	@Test
	public void getCustomerTest() {
		customerList.add(MOCK_CUSTOMER_A);

		Mockito.when(repo.findById(MOCK_CUSTOMER_A.id))
				.thenReturn((customerList.stream().filter(x -> x.id == MOCK_CUSTOMER_A.id).findFirst()));

		assertEquals(MOCK_CUSTOMER_A, service.findCustomer(MOCK_CUSTOMER_A.id));
		Mockito.verify(repo).findById(MOCK_CUSTOMER_A.id);
	}

	@Test
	public void getAllCustomerTest() {
		customerList.add(MOCK_CUSTOMER_A);
		customerList.add(MOCK_CUSTOMER_B);
		Mockito.when(repo.findAll()).thenReturn(customerList);
		assertEquals(customerList, service.findAll());
	}

	@Test
	public void deleteCustomerTest() {
		customerList.add(MOCK_CUSTOMER_A);
		String reply = service.deleteCustomer(MOCK_CUSTOMER_A.id);
		assertEquals(MOCK_DELETE_CUSTOMER_RESPONSE, reply);
		Mockito.verify(repo).deleteById(MOCK_CUSTOMER_A.id);
	}

}
