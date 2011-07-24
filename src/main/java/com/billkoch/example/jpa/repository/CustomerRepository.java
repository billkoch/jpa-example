package com.billkoch.example.jpa.repository;

import java.util.List;

import com.billkoch.example.jpa.domain.Customer;

public interface CustomerRepository {

	String save(Customer customer);

	List<Customer> withLastNameLike(String lastName);

	List<Customer> withFirstNameLike(String firstName);
}
