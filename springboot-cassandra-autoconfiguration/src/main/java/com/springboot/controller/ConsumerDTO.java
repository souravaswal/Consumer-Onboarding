package com.springboot.controller;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "consumer_table")
public class ConsumerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private String consumerGuid;
	
	@Column
	private String customerGuid;
	
	@Column
	private String emailId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	public String getConsumerGuid() {
		return consumerGuid;
	}

	public void setConsumerGuid(String consumerGuid) {
		this.consumerGuid = consumerGuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCustomerGuid() {
		return customerGuid;
	}

	public void setCustomerGuid(String customerGuid) {
		this.customerGuid = customerGuid;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	

}
