package com.springboot.controller;


import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exceptions.BadRequestException;
import com.springboot.exceptions.ResourceAlreadyExistsException;
import com.springboot.exceptions.ResourceNotFoundException;
import com.springboot.repository.ConsumerCassandraRepository;
import com.springboot.repository.CustomerCassandraRepository;

@RestController("ConsumerController")
@RequestMapping("/consumer-onboarding/api/v1")
public class ConsumerController {
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
    private ConsumerCassandraRepository consumerCassandraRepo;
	
	@Autowired
	private CustomerCassandraRepository customerCassandraRepository;

	@Autowired
	private ConsumerMapper consumerMapper;
	
	/***
	 * This is POST API for onboarding consumer under a specific customer
	 * @param customer
	 * @return customerResponse
	 */
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "{customerGuid}/consumer", consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
	public Consumer createConsumer(
			@RequestBody @Valid final Consumer consumer,
			@PathVariable(value = "customerGuid") @NonNull final String customerGuid)
	{
	LOG.info("Entering method createConsumer with consumer: {}", consumer);
	
	// check if incoming customer and consumer guid are valid or not
	isValidGuid(customerGuid, "Customer");
	isValidGuid(consumer.getConsumerGuid(), "Consumer");
	
	// Will create consumer if customer is present and consumer is not present
	if (!customerCassandraRepository.existsById(customerGuid)) {
		LOG.error("Customer doesn't exist with guid {}", customerGuid);
		throw new ResourceNotFoundException("Customer doesn't exist");
	}
	
	if (consumerCassandraRepo.existsById(consumer.getConsumerGuid())) {
		LOG.error("Consumer already exist with guid {}", consumer.getConsumerGuid());
		throw new ResourceAlreadyExistsException("Consumer already exists in database");
	}
	
	ConsumerDTO consumerDto = consumerMapper.consumerToDto(consumer);
	
	ConsumerDTO consumerDtoResponse = consumerCassandraRepo.save(consumerDto);
	
	Consumer consumerResponse = consumerMapper.dtoToConsumer(consumerDtoResponse);

	LOG.info("Exiting method createConsumer");
	
	return consumerResponse;

	}
	
	/***
	 * This is GET API for fetching customer information
	 * @param orgGuid
	 * @return customerResponse
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "{customerGuid}/consumer/{consumerGuid}", produces = {"application/json"}, method = RequestMethod.GET)
	public Consumer getConsumer(
			@PathVariable(value = "customerGuid") final String customerGuid,
			@PathVariable(value = "consumerGuid") final String consumerGuid)
	{
	LOG.info("Entering method getConsumer");
	
	// check if incoming customer and consumer guid are valid or not
	isValidGuid(customerGuid, "Customer");
	isValidGuid(consumerGuid, "Consumer");
	
	Optional<ConsumerDTO> lookupResult = consumerCassandraRepo.findById(consumerGuid);
	
	if(!lookupResult.isPresent()) {
		LOG.error("Consumer not found for customer guid {} and consumer guid {}", customerGuid, consumerGuid);
		throw new ResourceNotFoundException("Consumer doesn't exist");
	}
	
	ConsumerDTO consumerDtoResponse = lookupResult.get();
	
	Consumer consumerResponse = consumerMapper.dtoToConsumer(consumerDtoResponse);
	
	LOG.info("Exiting method getConsumer");

	return consumerResponse;
	}
	
	/***
	 * This is DELETE API for deleting customer information along a particular OrgGuid
	 * @param orgGuid
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{customerGuid}/consumer/{consumerGuid}", produces = {"application/json"}, method = RequestMethod.DELETE)
	public void deleteOrganisation(
			@PathVariable(value = "customerGuid") final String customerGuid,
			@PathVariable(value = "consumerGuid") final String consumerGuid)
	{
	LOG.info("Entering method deleteOrganisation");

	// check if incoming customer and consumer guid are valid or not
	isValidGuid(customerGuid, "Customer");
	isValidGuid(consumerGuid, "Consumer");

	consumerCassandraRepo.deleteById(consumerGuid);
	LOG.info("Exiting method deleteOrganisation");
	}
	
	/***
	 * This is PUT API for updating Consumer details under a particular OrgGuid
	 * @param customer
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "{customerGuid}/consumer/{consumerGuid}", produces = {"application/json"}, method = RequestMethod.PUT)
	public Consumer updateOrganisation(
			@PathVariable(value = "customerGuid") final String customerGuid, 
			@Valid @RequestBody Consumer consumer)
	{
	LOG.info("Entering method updateOrganisation");
	
	// check if incoming customer and consumer guid are valid or not
	isValidGuid(customerGuid, "Customer");
	isValidGuid(consumer.getConsumerGuid(), "Consumer");
	
	ConsumerDTO customerDto = consumerMapper.consumerToDto(consumer);
	ConsumerDTO customerDtoResponse = consumerCassandraRepo.save(customerDto);
	Consumer consumerResponse = consumerMapper.dtoToConsumer(customerDtoResponse);
	
	LOG.info("Exiting method updateOrganisation");
	return consumerResponse;
	}
	
	/**
	 * This method is used to check if guid is valid or not
	 * @param guid
	 * @param resource
	 * @return
	 */
	private boolean isValidGuid(final String guid, final String resource) {
		boolean isValid = false;
		try {
			UUID.fromString(guid);
			isValid = true;
		}
		catch(IllegalArgumentException exception) {
			LOG.error("{} guid is not valid", resource);
			throw new BadRequestException(resource.concat(" guid id not valid"));
		}
		return isValid;
	}
}
