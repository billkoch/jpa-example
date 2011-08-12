package com.billkoch.example.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.billkoch.example.jpa.domain.Account;
import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.AccountRepository;

@Component
public class AccountRepositoryJpaImpl implements AccountRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public String createNewAccount(Account account) {
		entityManager.persist(account);
		return account.getId();
	}

	@Override
	public List<Account> belongingToCustomer(Customer customer) {
		return entityManager.createNamedQuery("belongingToCustomer", Account.class).setParameter("customer", customer).getResultList();
	}
}
