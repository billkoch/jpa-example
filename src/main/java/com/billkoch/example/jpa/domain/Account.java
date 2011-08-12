package com.billkoch.example.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang.StringUtils;

@NamedQueries({
	@NamedQuery(name="belongingToCustomer", query="select a from Account a where a.customer = :customer")
})
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@ManyToOne
	private Customer customer;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj == this) {
			result = true;
			
		} else if(obj instanceof Account){
			Account that = (Account) obj;
			result = StringUtils.equals(this.id, that.id);
		}
		return result;
	}
	

	public static class Builder {
		
		private String id;
		
		private Customer customer;
		
		public Builder() {
		}
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder customer(Customer customer) {
			this.customer = customer;
			return this;
		}
		
		public Account build() {
			Account account = new Account();
			account.id = this.id;
			account.setCustomer(this.customer);
			return account;
		}
	}
}
