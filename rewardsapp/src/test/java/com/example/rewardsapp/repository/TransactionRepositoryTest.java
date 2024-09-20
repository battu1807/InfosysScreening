package com.example.rewardsapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.rewardsapp.entity.Transaction;

@DataJpaTest
class TransactionRepositoryTest {

	@Autowired
	TransactionRepository transactionRepository;
	Transaction transaction1;
	Transaction transaction2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		transaction1 = new Transaction(1, "books", 500, LocalDate.now(), 1);
		transaction2 = new Transaction(2, "books", 500, LocalDate.now(), 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionRepository.deleteAll();
	}

	@Test
	void testSave() {
		Transaction t1 = transactionRepository.save(transaction1);
		assertNotNull(t1);
		assertEquals(t1.getAmount(), transaction1.getAmount());
	}
	
	@Test 
	void testFindByTransactionId() {
		transactionRepository.save(transaction2);
		Transaction t2 = transactionRepository.findByTransactionId(2);
		assertNotNull(t2);
		assertEquals(t2.getAmount(), transaction2.getAmount());
	}

}
