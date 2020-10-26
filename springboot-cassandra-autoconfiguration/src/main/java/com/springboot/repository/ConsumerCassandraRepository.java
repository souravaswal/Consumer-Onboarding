package com.springboot.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.controller.ConsumerDTO;

@Repository
public interface ConsumerCassandraRepository extends CrudRepository<ConsumerDTO, Serializable> {
	
//	@Query("SELECT * FROM consumer_table WHERE (orgGuid = ?0 AND consumerGuid = ?1)")
//	Optional<ConsumerDTO> findByConsumerAndCustomerGuid(String orgGuid, String consumerGuid);

}