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

import com.billkoch.example.jpa.domain.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/databaseTestApplicationContext.xml"})
public class AccountRepositoryJpaImplIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AccountRepository uut;
	
	private Account account;
	
	@Before
	public void setup() {
		account = new Account();
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
}
