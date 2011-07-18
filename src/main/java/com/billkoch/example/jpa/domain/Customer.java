package com.billkoch.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name="last_name")
	private String lastName;
	
	public Customer() {
		this("");
	}
	
	public Customer(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(this == obj) {
			result = true;
			
		} else if(obj instanceof Customer) {
			Customer that = (Customer) obj;
			result = this.id.equals(that.id);
		}
		return result;
	}
}
