package com.billkoch.example.jpa.repository.impl;

import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.billkoch.example.jpa.domain.Account;
import com.billkoch.example.jpa.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryJpaImplTest {

	private AccountRepository uut;
	
	@Mock
	private EntityManager mockEntityManager;
	
	private Account account;
	
	@Before
	public void setup() {
		uut = new AccountRepositoryJpaImpl();
		((AccountRepositoryJpaImpl) uut).entityManager = mockEntityManager;
		account = new Account();
	}
	
	@Test
	public void createNewAccountShouldInteractWithTheEntityManager() {
		uut.createNewAccount(account);

		verify(mockEntityManager).persist(account);
	}
}
