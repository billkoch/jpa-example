package com.billkoch.example.jpa.repository.impl;

import org.junit.Test;

import com.billkoch.example.jpa.domain.Account;

public class AccountRepositoryJpaImplTest {

	@Test
	public void createNewAccountShouldInteractWithTheEntityManager() {
		AccountRepository uut = new AccountRepositoryJpaImpl();
		String accountId = uut.createNewAccount(new Account());
	}
}
