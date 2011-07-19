package com.billkoch.example.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;

@Repository
public class CustomerRepositoryJpaImpl implements CustomerRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public String save(Customer customer) {
		entityManager.persist(customer);
		return customer.getId();
	}

	@Override
	public List<Customer> withLastNameLike(String lastName) {
		return entityManager.createQuery("select c from Customer c where lastName like :lastName").setParameter("lastName", lastName).getResultList();
	}
}
