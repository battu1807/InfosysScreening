package com.example.rewardsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.entity.Transaction;
import com.example.rewardsapp.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customer")
@Validated
public class CustomerController {

	@Autowired
	CustomerService customerService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
	
	@PostMapping(path="/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer customer) {
		return customerService.verify(customer);
	}
	
	@GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
          
        return "redirect:/login?logout";
    }
}