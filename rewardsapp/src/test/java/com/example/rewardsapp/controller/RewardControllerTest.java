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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RewardControllerTest {

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
    	
    	String transactionJson = objectMapper.writeValueAsString(transaction);

        mockMvc.perform(post("/order")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123"))
        		.contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isCreated());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void testGetAllRewards() throws Exception{
		mockMvc.perform(get("/reward/all")
	        	.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123")))
	        	.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void testGetAllRewardsAuthFailed() throws Exception{
		mockMvc.perform(get("/reward/all")
	        	.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudras", "rudra@123")))
	        	.andExpect(status().isUnauthorized());
	}
	
	@Test
	@Order(2)
	void testGetRewardsByCustomerId() throws Exception{
		mockMvc.perform(get("/reward/customer/1")
	        	.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123")))
	        	.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void testGetTotalPoints() throws Exception {
		mockMvc.perform(get("/reward/total")
	        	.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123")))
	        	.andExpect(status().isOk());
	}

}
