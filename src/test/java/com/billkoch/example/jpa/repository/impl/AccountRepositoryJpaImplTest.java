package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.billkoch.example.jpa.domain.Account;
import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.AccountRepository;
import com.google.common.collect.Lists;


@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryJpaImplTest {

	private AccountRepository uut;
	
	@Mock
	private EntityManager mockEntityManager;
	
	@Mock
	private TypedQuery<Account> mockQuery;
	
	private Account account;
	
	@Before
	public void setup() {
		uut = new AccountRepositoryJpaImpl();
		((AccountRepositoryJpaImpl) uut).entityManager = mockEntityManager;
		
		account = new Account.Builder().customer(new Customer()).build();
	}
	
	@Test
	public void createNewAccountShouldInteractWithTheEntityManager() {
		uut.createNewAccount(account);

		verify(mockEntityManager).persist(account);
	}
	
	@Test
	public void retrievingAccountsByCustomer() {
		when(mockEntityManager.createNamedQuery(anyString(), (Class<Account>) anyObject())).thenReturn(mockQuery);
		when(mockQuery.setParameter(anyString(), anyObject())).thenReturn(mockQuery);
		
		List<Account> expectedAccountList = Lists.newArrayList(new Account());
		when(mockQuery.getResultList()).thenReturn(expectedAccountList);
		
		List<Account> belongingToCustomer = uut.belongingToCustomer(new Customer());

		assertThat(belongingToCustomer, is(expectedAccountList));
		
		verify(mockEntityManager).createNamedQuery(Mockito.anyString(), (Class<Account>) Mockito.anyObject());
		verify(mockQuery).setParameter(anyString(), anyObject());
		verify(mockQuery).getResultList();
	}
}
