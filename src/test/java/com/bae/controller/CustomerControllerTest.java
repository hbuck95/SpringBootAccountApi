package com.bae.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bae.entity.Customer;
import com.bae.service.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController controller;

	@Mock
	private CustomerService service;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private JmsTemplate jmsTemplate;

	private List<Customer> customerList;
	private JmsMessagingTemplate messagingTemplate;

	private static final Customer MOCK_CUSTOMER_A = new Customer("1", "Fred", "Derf", "B123456");
	private static final Customer UPDATED_MOCK_CUSTOMER_A = new Customer("1", "Mike", "Truk", "B123456");
	private static final Customer MOCK_CUSTOMER_B = new Customer("2", "Bobson", "Dugnutt", "A456789");
	private static final String MOCK_CREATE_CUSTOMER_RESPONSE = "{\"Status\":\"Customer created\",\"Prize\":0}";
	private static final String MOCK_DELETE_CUSTOMER_RESPONSE = "Customer deleted.";
	private static final ResponseEntity<String> MOCK_NUMGEN_RESPONSE = new ResponseEntity<>(
			MOCK_CUSTOMER_A.getAccountNumber(), HttpStatus.OK);
	private static final ResponseEntity<Integer> MOCK_PRIZEGEN_RESPONSE = new ResponseEntity<>(50, HttpStatus.OK);
	private static final String NUM_GEN_URL = "http://localhost:8082/numgen";
	private static final String PRIZE_GEN_URL = "http://localhost:8081/prizegen/B123456";

	@Before
	public void setup() {
		customerList = new ArrayList<>();
		this.messagingTemplate = new JmsMessagingTemplate();
	}

	@Test
	public void numGenMicroServiceTest() {
		Mockito.when(restTemplate.exchange(NUM_GEN_URL, HttpMethod.GET, null, String.class))
				.thenReturn(MOCK_NUMGEN_RESPONSE);
	}

	@Test
	public void prizeGenMicroServiceTest() {
		Mockito.when(restTemplate.exchange(PRIZE_GEN_URL, HttpMethod.GET, null, Integer.class))
				.thenReturn(MOCK_PRIZEGEN_RESPONSE);
	}

	@Test
	public void createCustomerTest() throws JMSException {
		Mockito.when(restTemplate.exchange(NUM_GEN_URL, HttpMethod.GET, null, String.class))
				.thenReturn(MOCK_NUMGEN_RESPONSE);
		Mockito.when(restTemplate.exchange(PRIZE_GEN_URL, HttpMethod.GET, null, Integer.class))
				.thenReturn(MOCK_PRIZEGEN_RESPONSE);
		Mockito.when(service.createCustomer(MOCK_CUSTOMER_A)).thenReturn(MOCK_CREATE_CUSTOMER_RESPONSE);

		String reply = controller.createCustomer(MOCK_CUSTOMER_A);
		assertEquals(MOCK_CREATE_CUSTOMER_RESPONSE, reply);
		Mockito.verify(service).createCustomer(MOCK_CUSTOMER_A);
	}

}
