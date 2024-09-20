package com.example.rewardsapp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int customerId;
	@NotNull(message = "Customer Name is mandatory")
	private String customerName;
	private String emailId;
	@Pattern(regexp="(^$|[0-9]{10})")
	@Size(min=10, max=10, message="Phone number should contains exact 10 numbers")
	private String phoneNumber;
	@NotNull(message = "Password Name is mandatory")
	private String password;
}
