package com.springboot.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.controller.CustomerDTO;

@Repository
public interface CustomerCassandraRepository extends CrudRepository<CustomerDTO, Serializable> {
	
//	Optional<SimpleTable> findByIdAndName(String id, String name);

}