package com.billkoch.example.jpa.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WhenDeterminingIfTwoAccountsAreEqual {

	@Test
	public void accountsThatPointToTheSameMemoryLocationShouldBeEqual() {
		Account leftSide = new Account();
		Account rightSide = leftSide;

		assertThat(leftSide, is(equalTo(rightSide)));
	}
	
	@Test
	public void accountsWithTheSameIdShouldBeEqual() {
		Account leftSide = new Account.Builder().id("1").build();
		Account rightSide = new Account.Builder().id("1").build();
		
		assertThat(leftSide, is(equalTo(rightSide)));
	}
	
	@Test
	public void accountsWithDifferentIdsShouldNotBeEqual() {
		Account leftSide = new Account.Builder().id("1").build();
		Account rightSide = new Account.Builder().id("2").build();
		
		assertThat(leftSide, is(not(equalTo(rightSide))));
	}
	
	@Test
	public void accountsWithNullIdsShouldBeHandledGracefully() {
		Account leftSide = new Account.Builder().id(null).build();
		Account rightSide = new Account.Builder().id("2").build();
		
		assertThat(leftSide, is(not(equalTo(rightSide))));
		assertThat(rightSide, is(not(equalTo(leftSide))));
	}
	
	@Test
	public void accountsShouldNotBeEqualToOtherTypes() {
		Account anAccount = new Account.Builder().id(null).build();
		Object notAnAccountType = new Object();
		
		assertFalse(anAccount.equals(notAnAccountType));
	}
}
