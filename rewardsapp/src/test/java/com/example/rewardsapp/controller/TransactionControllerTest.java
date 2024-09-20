package com.example.rewardsapp.controller;

import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.entity.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // for converting objects to JSON
    private Customer customer;
    private Transaction transaction;

	@BeforeEach
	void setUp() throws Exception {
		
		customer = new Customer();
    	customer.setCustomerId(1);
    	customer.setCustomerName("rudra");
    	customer.setEmailId("rudra@gmail.com");
    	customer.setPassword("rudra@123");
    	customer.setPhoneNumber("123123");
        String customerJson = objectMapper.writeValueAsString(customer);

    	mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson));
    	
    	mockMvc.perform(post("/customer/login")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson));
    	transaction = new Transaction();
    	transaction.setTransactionId(1);
    	transaction.setAmount(100);
    	transaction.setCustomerId(1);
    	transaction.setDate(LocalDate.now());
    	transaction.setSpentDetails("Books");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(3)
	void testGetTransaction() throws Exception{
        	mockMvc.perform(get("/order/1")
        	.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123")))
        	.andExpect(status().isOk());
	}

	@Test
	@Order(1)
	void testPostTransaction() throws Exception{
		
        String transactionJson = objectMapper.writeValueAsString(transaction);

        mockMvc.perform(post("/order")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123"))
        		.contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isCreated());
	}

	@Test
	void testUpdateTransaction() {
	}

	@Test
	void testDeleteTransaction() {
	}

	@Test
	@Order(2)
	void testPostMultipleTransactions() throws Exception{
		List<Transaction> listOfTransaction = new ArrayList<>();
		listOfTransaction.add(new Transaction(2,"cloths", 300, LocalDate.now(), 1));
		listOfTransaction.add(new Transaction(3,"clothsold", 90, LocalDate.now(), 1));
		listOfTransaction.add(new Transaction(4,"clothnew", 100, LocalDate.now(), 1));
		String transactionJson = objectMapper.writeValueAsString(listOfTransaction);

        mockMvc.perform(post("/order/postMultiple")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123"))
        		.contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isCreated());
	}

}
