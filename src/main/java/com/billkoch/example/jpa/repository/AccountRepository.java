package com.billkoch.example.jpa.repository;

import com.billkoch.example.jpa.domain.Account;

public interface AccountRepository {

	String createNewAccount(Account account);
}
