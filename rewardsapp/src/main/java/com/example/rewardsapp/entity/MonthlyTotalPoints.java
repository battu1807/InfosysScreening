package com.example.rewardsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyTotalPoints {

	private int customerId;
	private String rewardMonth;
	private int rewardYear;
	private int monthlyTotalPoints;
	
}
