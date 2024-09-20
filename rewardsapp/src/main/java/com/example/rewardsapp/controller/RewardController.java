package com.example.rewardsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewardsapp.entity.Reward;
import com.example.rewardsapp.entity.TotalPoints;
import com.example.rewardsapp.service.RewardService;

@RestController
@RequestMapping("reward")
public class RewardController {

	@Autowired
	RewardService rewardService;

	@GetMapping(path="/all") 
	public ResponseEntity<List<Reward>> getAllRewards(){ 
		
		return new ResponseEntity<>(rewardService.getAllRewards(), HttpStatus.OK); 
	}

	@GetMapping(path="/customer/{customerId}") 
	public ResponseEntity<List<Reward>> getRewardsByCustomerId(@PathVariable int customerId){ // Reward reward =
		
		return new ResponseEntity<>(rewardService.getRewardsByCustomerId(customerId), HttpStatus.OK); 
	}
	
	@GetMapping(path="/total") 
	public ResponseEntity<List<TotalPoints>> getTotalPoints(){ 
		
		return new ResponseEntity<List<TotalPoints>>(rewardService.getTotalRewards(), HttpStatus.OK); 
	}
	
	

}
