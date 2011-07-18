package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/databaseTestApplicationContext.xml")
public class CustomerRepositoryJpaImplIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CustomerRepository uut;

	private Customer customer;

	@Before
	public void setup() {
		customer = new Customer("Koch");
	}

	@Test
	public void savingACustomerShouldPersistTheCustomerInTheDatabase() {
		String customerId = this.uut.save(customer);

		assertThat(customerId, is(notNullValue()));
		assertThat(customerId, is(not(equalTo(""))));
		
		Customer persistedCustomer = super.simpleJdbcTemplate.queryForObject("select id from customer c where c.id=?"
				, new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
						Customer persistedCustomer = new Customer();
						persistedCustomer.setId(resultSet.getString("id"));
						return persistedCustomer;
					}
				}
				, new Object[]{customerId});
		
		assertThat(customer, is(persistedCustomer));
	}
}
