package com.billkoch.example.jpa.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.billkoch.example.jpa.domain.Account;

@Component
public class AccountRepositoryJpaImpl implements AccountRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public String createNewAccount(Account account) {
		entityManager.persist(account);
		return account.getId();
	}
}
