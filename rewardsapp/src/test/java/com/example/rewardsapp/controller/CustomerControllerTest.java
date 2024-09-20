package com.example.rewardsapp.controller;

import com.example.rewardsapp.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // for converting objects to JSON

    private Customer customer;

    @BeforeEach
    void setUp() {
    	customer = new Customer();
    	customer.setCustomerId(1);
    	customer.setCustomerName("rudra");
    	customer.setEmailId("rudra@gmail.com");
    	customer.setPassword("rudra@123");
    	customer.setPhoneNumber("1231231234");
    }

    @Test
    @Order(1)
    public void testRegister() throws Exception {
    	System.out.println("CustomerControllerTest.testRegister()");
        String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.name").value("Sample Item"));
    }
    
    @Test
    @Order(2)
    public void testLoginSuccess() throws Exception{
    	System.out.println("CustomerControllerTest.testLoginSuccess()");
    	String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/customer/login")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudra", "rudra@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isOk());
        
    }
    
    @Test
    @Order(3)
    public void testLoginFailed() throws Exception{
    	System.out.println("CustomerControllerTest.testLoginSuccess()");
    	String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/customer/login")
        		.with(SecurityMockMvcRequestPostProcessors.httpBasic("rudras", "rudra@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isUnauthorized());
        
    }
}