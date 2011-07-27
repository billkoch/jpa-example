package com.billkoch.example.jpa.repository.impl;

import javax.persistence.EntityManager;

import com.billkoch.example.jpa.domain.Account;

public class AccountRepositoryJpaImpl implements AccountRepository {

	protected EntityManager entityManager;
	
	@Override
	public String createNewAccount(Account account) {
		entityManager.persist(account);
		return null;
	}
}
