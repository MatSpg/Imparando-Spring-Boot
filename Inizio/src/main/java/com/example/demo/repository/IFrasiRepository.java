package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Frasi;

@Repository
public interface IFrasiRepository extends CrudRepository<Frasi, Integer> {

	
	
}
