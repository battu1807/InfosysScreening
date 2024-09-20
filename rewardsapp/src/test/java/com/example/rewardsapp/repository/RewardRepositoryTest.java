package com.example.rewardsapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.rewardsapp.entity.Reward;


@DataJpaTest
class RewardRepositoryTest {
	
	@Autowired
	RewardRepository rewardRepository;
	Reward reward;
	
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
	void testSaveReward() {
		Reward r1 = new Reward(1, "FEBRUARY", 2024, 100, 1);
		assertNotNull(rewardRepository.save(r1));
		assertNotNull(rewardRepository.findById(1));
	}
	
	@Test
	void testFindAll() {
		Reward r1 = new Reward(1, "FEBRUARY", 2024, 100, 1);
		assertNotNull(rewardRepository.save(r1));
		r1 = new Reward(2, "MARCH", 2023, 200, 1);
		assertNotNull(rewardRepository.save(r1));
		assertNotNull(rewardRepository.findAll());
	}
	
	

}
