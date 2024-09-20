package com.example.rewardsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.entity.Reward;
import com.example.rewardsapp.entity.TotalPoints;
import com.example.rewardsapp.entity.Transaction;

import jakarta.persistence.Tuple;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
	List<Reward> findByCustomerId(Integer customerId);
	List<Reward> findAll();
	
	@Query(value = "select customer_id as customerId, sum(points) as totalPoints  from reward \r\n"
			+ "	group by customer_id;", nativeQuery = true)
    List<Tuple> findTotalPointsGroupByCustomerId();
	
	@Query(value = "select customer_id as customerId, reward_month as rewardMonth, reward_year as rewardYear, sum(points) as monthlyTotalPoints  from reward \r\n"
			+ "	group by customer_id, reward_month, reward_year\r\n", nativeQuery = true)
    List<Tuple> findTotalPointsGroupByCustomerIdAndMonth();
	
	
	
	
}
