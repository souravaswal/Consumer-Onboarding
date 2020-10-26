package com.springboot.controller;

import org.springframework.lang.NonNull;

public class Consumer {
	
	@NonNull
	private String customerGuid;
	
	@NonNull
	private String consumerGuid;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;

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
