package com.example.rewardsapp.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalPoints {

	private int customerId;
	private int totalPoints;
	private List<MonthlyTotalPoints> listOfMonthlyTotalPoints;
	
}
