package com.springboot.controller;

import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {
	public ConsumerDTO consumerToDto(Consumer consumer) {
		ConsumerDTO consumerDto = new ConsumerDTO();
		consumerDto.setConsumerGuid(consumer.getConsumerGuid());
		consumerDto.setCustomerGuid(consumer.getCustomerGuid());
		consumerDto.setFirstName(consumer.getFirstName());
		consumerDto.setLastName(consumer.getLastName());
		consumerDto.setEmailId(consumer.getEmailId());
		return consumerDto;
	}
	
	public Consumer dtoToConsumer(ConsumerDTO consumerDto) {
		Consumer consumer = new Consumer();
		consumer.setConsumerGuid(consumerDto.getConsumerGuid());
		consumer.setCustomerGuid(consumerDto.getCustomerGuid());
		consumer.setFirstName(consumerDto.getFirstName());
		consumer.setLastName(consumerDto.getLastName());
		consumer.setEmailId(consumerDto.getEmailId());
		return consumer;
	}
}
