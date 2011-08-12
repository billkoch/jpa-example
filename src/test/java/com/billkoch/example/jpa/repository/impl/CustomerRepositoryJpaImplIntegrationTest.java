package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	private Customer expectedCustomer;

	@Before
	public void setup() {
		expectedCustomer = new Customer.Builder().id("1").firstName("Bill").lastName("Koch").build();
	}

	@Test
	public void savingACustomerShouldPersistTheCustomerInTheDatabase() {
		Customer aBrandNewCustomer = new Customer.Builder().firstName("Bill").lastName("Koch").build();
		String customerId = uut.save(aBrandNewCustomer);

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
		
		assertThat(aBrandNewCustomer, is(persistedCustomer));
	}
	
	@Test
	public void withLastNameLikeShouldReturnListOfCustomers() {
		List<Customer> customersWithLastNameLikeKoch = uut.withLastNameLike("Koch");
		
		assertThat(customersWithLastNameLikeKoch, is(notNullValue()));
		assertThat(customersWithLastNameLikeKoch, hasSize(1));
		assertThat(expectedCustomer, isIn(customersWithLastNameLikeKoch));
	}
	
	@Test
	public void withFirstNameLikeShouldReturnListOfCustomers() {
		List<Customer> customersWithFirstNameLikeBill = uut.withFirstNameLike("Bill");
		
		assertThat(customersWithFirstNameLikeBill, is(notNullValue()));
		assertThat(customersWithFirstNameLikeBill, hasSize(1));
	}
}
