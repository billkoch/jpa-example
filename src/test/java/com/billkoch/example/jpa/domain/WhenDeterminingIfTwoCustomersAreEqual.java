package com.billkoch.example.jpa.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WhenDeterminingIfTwoCustomersAreEqual {

	@Test
	public void customersThatPointToTheSameMemoryLocationShouldBeEqual() {
		Customer leftSide = new Customer.Builder().id("1").build();
		Customer rightSide = leftSide;

		assertThat(leftSide, is(equalTo(rightSide)));
	}
	
	@Test
	public void customersWithTheSameIdShouldBeEqual() {
		Customer leftSide = new Customer.Builder().id("1").build();
		Customer rightSide = new Customer.Builder().id("1").build();
		
		assertThat(leftSide, is(equalTo(rightSide)));
	}
	
	@Test
	public void customersWithDifferentIdsShouldNotBeEqual() {
		Customer leftSide = new Customer.Builder().id("1").build();
		Customer rightSide = new Customer.Builder().id("2").build();
		
		assertThat(leftSide, is(not(equalTo(rightSide))));
	}
	
	@Test
	public void customersWithNullIdsShouldBeHandledGracefully() {
		Customer leftSide = new Customer.Builder().id(null).build();
		Customer rightSide = new Customer.Builder().id(null).build();
		
		assertThat(leftSide, is(equalTo(rightSide)));
	}
	
	@Test
	public void customersShouldNotBeEqualToOtherTypes() {
		Customer leftSide = new Customer();
		Object notACustomerType = new Object();
		
		assertFalse(leftSide.equals(notACustomerType));
	}
}
