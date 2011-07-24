package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryJpaImplTest {

	private CustomerRepository uut;

	@Mock
	private EntityManager mockEntityManager;

	@Mock
	private TypedQuery<Customer> mockQuery;

	private Customer customer;

	@Before
	public void setup() {
		uut = new CustomerRepositoryJpaImpl();
		((CustomerRepositoryJpaImpl) uut).entityManager = mockEntityManager;

		customer = new Customer();
	}

	@Test
	public void savingACustomerShouldInteractWithTheEntityManager() {
		uut.save(customer);
		verify(mockEntityManager).persist(customer);
	}

	@Test
	public void retrievingCustomersWithLastNameLikeShouldInteractWithTheEntityManager() {
		when(mockEntityManager.createQuery(anyString(), (Class<Customer>) anyObject())).thenReturn(mockQuery);
		when(mockQuery.setParameter(anyString(), anyString())).thenReturn(mockQuery);

		List<Customer> expectedCustomerList = Lists.newArrayList(new Customer("Koch", "Bill"));
		when(mockQuery.getResultList()).thenReturn(expectedCustomerList);

		List<Customer> customersWithLastNameLikeKoch = uut.withLastNameLike("Koch");

		assertThat(customersWithLastNameLikeKoch, is(notNullValue()));

		verify(mockEntityManager).createQuery(anyString(), (Class<Customer>) anyObject());
		verify(mockQuery).setParameter(anyString(), anyString());
		verify(mockQuery).getResultList();
	}

	@Test
	public void retrievingCustomersWithFirstNameLikeShouldInteractWithTheEntityManager() {
		when(mockEntityManager.createQuery(anyString(), (Class<Customer>) anyObject())).thenReturn(mockQuery);
		when(mockQuery.setParameter(anyString(), anyString())).thenReturn(mockQuery);

		List<Customer> expectedCustomerList = Lists.newArrayList(new Customer("Bill", "Koch"));
		when(mockQuery.getResultList()).thenReturn(expectedCustomerList);

		List<Customer> customersWithFirstNameLikeBill = uut.withFirstNameLike("Bill");

		assertThat(customersWithFirstNameLikeBill, is(expectedCustomerList));

		verify(mockEntityManager).createQuery(anyString(), (Class<Customer>) anyObject());
		verify(mockQuery).setParameter(anyString(), anyString());
		verify(mockQuery).getResultList();
	}
}
