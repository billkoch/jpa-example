package com.billkoch.example.jpa.repository.impl;

import javax.persistence.EntityManager;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;

public class CustomerRepositoryJpaImpl implements CustomerRepository {

	protected EntityManager entityManager;

	@Override
	public String save(Customer customer) {
		entityManager.persist(customer);
		return "";
	}
}
