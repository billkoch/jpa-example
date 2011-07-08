package com.billkoch.example.jpa.repository;

import com.billkoch.example.jpa.domain.Customer;

public interface CustomerRepository {

	String save(Customer customer);
}
