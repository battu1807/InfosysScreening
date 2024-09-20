package com.example.rewardsapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewardsapp.entity.Transaction;
import com.example.rewardsapp.exception.CustomerNotFoundException;
import com.example.rewardsapp.repository.CustomerRepository;
import com.example.rewardsapp.service.TransactionService;

@RestController
@RequestMapping("order")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping(path="/{transactionId}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable int transactionId){
		return new ResponseEntity<Transaction>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction transaction) throws Exception{
		logger.debug("transaction.getCustomerId() : " + transaction.getCustomerId());
		if(customerRepository.findByCustomerId(transaction.getCustomerId()) == null) {
			logger.error("Customer not exist");
			throw new CustomerNotFoundException("Customer not exist " + transaction.getCustomerId());
			
//			return new ResponseEntity("Customer not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Transaction>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){
		return new ResponseEntity<Transaction>(transactionService.updateTransaction(transaction), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{transactionId}")
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable int transactionId){
		transactionService.deleteById(transactionId);
		return new ResponseEntity("Transaction is deleted successfully", HttpStatus.OK);
	}
	
	
	@PostMapping(path="/postMultiple")
	public ResponseEntity<String> postMultipleTransactions(@RequestBody List<Transaction> listOfTransactions){
		
		int count = 0;
		
		for (Transaction transaction : listOfTransactions) {
			logger.debug("transaction.getCustomerId() : " + transaction.getCustomerId());
			if(customerRepository.findByCustomerId(transaction.getCustomerId()) == null) {
				logger.error("Customer not exist : " + transaction.getCustomerId());
				continue;
			}
			transactionService.saveTransaction(transaction);
			count++;
		}
		
		
		return new ResponseEntity("Total " + count + " transactions saved...", HttpStatus.CREATED);
	}
	
}
