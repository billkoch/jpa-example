package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryJpaImplTest {

	private CustomerRepository uut;
	
	@Mock
	private EntityManager mockEntityManager;

	private Customer customer;

	@Before
	public void setup() {
		uut = new CustomerRepositoryJpaImpl();
		((CustomerRepositoryJpaImpl) uut).entityManager = mockEntityManager;
		
		customer = new Customer();
	}

	@Test
	public void savingACustomerShouldInteractWithTheEntityManager() {
		String id = uut.save(customer);

		verify(mockEntityManager).persist(customer);
		assertThat(id, is(notNullValue()));
	}
}
