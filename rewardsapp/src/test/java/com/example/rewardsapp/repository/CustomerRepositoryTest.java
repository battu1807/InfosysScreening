package com.example.rewardsapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.rewardsapp.entity.Customer;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;
	Customer customer;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    public void testFindByCustomerId_Found() {
		customer = new Customer(1, "riya", "riya@gmail.com", "1231231234", "riya@123");
		customerRepository.save(customer);
		Customer retrievedCustomer = customerRepository.findByCustomerId(1);
		assertNotNull(retrievedCustomer);
//		assertThat(retrievedCustomer.getCustomerId()).isEqualTo(customer.getCustomerId());
//		assertThat(retrievedCustomer.getCustomerName()).isEqualTo(customer.getCustomerName());
//		assertThat(retrievedCustomer.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
//		assertThat(retrievedCustomer.getEmailId()).isEqualTo(customer.getEmailId());	
	}
	
	@Test
	public void testFindByCustomerName_Found() {
		Customer c1 = customerRepository.findByCustomerName("riya");
		assertNotNull(c1);
		assertThat(c1.getCustomerId()).isEqualTo(customer.getCustomerId());
		assertThat(c1.getCustomerName()).isEqualTo(customer.getCustomerName());
		assertThat(c1.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
		assertThat(c1.getEmailId()).isEqualTo(customer.getEmailId());	

	}
	
	@Test
	public void testFindByCustomerName_NotFound() {
		Customer c2 = customerRepository.findByCustomerName("riya1");
		assertNull(c2);
	}
}
