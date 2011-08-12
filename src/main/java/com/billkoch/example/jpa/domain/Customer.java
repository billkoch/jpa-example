package com.billkoch.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang.StringUtils;

@NamedQueries({
	@NamedQuery(name="withLastNameLike", query="select c from Customer c where lastName like :lastName"),
	@NamedQuery(name="withFirstNameLike", query="select c from Customer c where firstName like :firstName")
})
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	public Customer() {
		this("", "");
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj == this) {
			result = true;
			
		} else if(obj instanceof Customer) {
			Customer that = (Customer) obj;
			result = StringUtils.equals(this.id, that.id);
		}
		return result;
	}
	
	public static class Builder {
		
		private String id;
		
		private String lastName;
		
		private String firstName;
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Customer build() {
			Customer customer = new Customer();
			customer.id = this.id;
			customer.firstName = this.firstName;
			customer.lastName = this.lastName;
			return customer;
		}
	}
}
