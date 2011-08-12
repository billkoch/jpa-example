package com.billkoch.example.jpa.repository;

import java.util.List;

import com.billkoch.example.jpa.domain.Account;
import com.billkoch.example.jpa.domain.Customer;

public interface AccountRepository {

	String createNewAccount(Account account);

	List<Account> belongingToCustomer(Customer customer);
}
