package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/databaseTestApplicationContext.xml")
public class CustomerRepositoryJpaImplIntegrationTest {

	@Autowired
	private CustomerRepository uut;

	private Customer customer;

	@Before
	public void setup() {
		customer = new Customer();
	}

	@Test
	@Transactional
	public void savingACustomerShouldPersistTheCustomerInTheDatabase() {
		String customerId = this.uut.save(customer);

		assertThat(customerId, is(notNullValue()));
		assertThat(customerId, is(not(equalTo(""))));
	}
}
