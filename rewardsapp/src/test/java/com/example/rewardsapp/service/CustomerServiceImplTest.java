package com.example.rewardsapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.repository.CustomerRepository;

class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	JWTService jwtService;
	
	private CustomerService customerService;
	Customer customer;
	AutoCloseable autoCloseable;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		Authentication authentication = mock(Authentication.class);
		authenticationManager.authenticate(authentication);
		customerService = new CustomerServiceImpl(customerRepository,authenticationManager,jwtService);
		customer = new Customer(2, "satya", "satya@gmail.com", "1231231231", "satya@123");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveCustomer() {
		mock(Customer.class);
		mock(CustomerRepository.class);
		when(customerRepository.save(customer)).thenReturn(customer);
		
		assertThat(customerService.saveCustomer(customer)).isEqualTo(customer);
	}

	@Test
	void testVerify() {
		
		mock(Customer.class);
		mock(CustomerRepository.class);
		
		mock(JWTService.class);
		
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer rightCustomer = new Customer(2, "satya", "satya@gmail.com", "1231231231", "satya@123");
		Customer wrongCustomer = new Customer(3, "satyaaaa", "satya@gmail.com", "1231231231", "satya@123");
		
		Authentication authentication = mock(Authentication.class);
	    when(authentication.isAuthenticated()).thenReturn(true);

	    // Set up the SecurityContext with mock authentication
	    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(authentication);
	    SecurityContextHolder.setContext(securityContext);
		
		assertEquals("Failed", customerService.verify(wrongCustomer) );
		assertNotEquals("Failed", customerService.verify(rightCustomer) );
	}

}
