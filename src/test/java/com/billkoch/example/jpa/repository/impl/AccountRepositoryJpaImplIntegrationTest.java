package com.billkoch.example.jpa.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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

import com.billkoch.example.jpa.domain.Account;
import com.billkoch.example.jpa.domain.Customer;
import com.billkoch.example.jpa.repository.AccountRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/databaseTestApplicationContext.xml"})
public class AccountRepositoryJpaImplIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AccountRepository uut;
	
	private Account account;
	
	@Before
	public void setup() {
		account = new Account.Builder().customer(new Customer()).build();
	}

	@Test
	public void savingAnAccountShouldPersistTheAccountInTheDatabase() {
		String accountId = uut.createNewAccount(account);
		
		assertThat(accountId, is(notNullValue()));
		assertThat(accountId, is(not(equalTo(""))));
		
		Account persistedAccount = simpleJdbcTemplate.queryForObject("select id from account a where a.id=?", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Account persistedAccount = new Account();
				persistedAccount.setId(resultSet.getString("id"));
				return persistedAccount;
			}

		}, new Object[]{accountId});
		
		assertThat(account, is(persistedAccount));
	}
	
	@Test
	public void retrievingAccountsByCustomer() {
		List<Account> accounts = uut.belongingToCustomer(new Customer.Builder().id("1").build());
		
		List<Account> expectedAccounts = simpleJdbcTemplate.query("select a.id, c.id, c.last_name, c.first_name from account a inner join customer c on (a.customer_id = c.id) where a.id=?", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Customer persistedCustomer = new Customer.Builder().id(resultSet.getString(2)).lastName(resultSet.getString(3)).firstName(resultSet.getString(4)).build();
				
				Account persistedAccount = new Account.Builder().id(resultSet.getString(1)).customer(persistedCustomer).build();
				return persistedAccount;
			}
				
		}, new Object[]{"1"});
		
		assertThat(accounts, is(notNullValue()));
		assertThat(accounts, hasSize(1));
		assertThat(accounts, is(expectedAccounts));
	}
}
