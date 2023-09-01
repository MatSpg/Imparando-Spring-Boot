package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Frasi;
import com.example.demo.repository.IFrasiRepository;

//Per dare uno nome specifico al service per richiamarlo attraverso la dipendency injection
@Service("dbFrasiService")
public class DbFrasiService implements IFrasiService {
	
	@Autowired
	private IFrasiRepository frasiRepository;
	
	@Override
	public Iterable<Frasi> getAll() {
		return frasiRepository.findAll();
	}
	
	@Override
	public Optional<Frasi> getById(int id) {
		return frasiRepository.findById(id);
	}
	 
	@Override
	public Frasi create(Frasi frase) {		
		return frasiRepository.save(frase);
	}
	 
	@Override
	public Optional<Frasi> update(int id, String frase) {
		Optional<Frasi> foundFrase = frasiRepository.findById(id);
		
		if(foundFrase.isEmpty()) {
			return Optional.empty();
		}
		
		foundFrase.get().setFrase(frase);
		
		frasiRepository.save(foundFrase.get());
		
		return foundFrase;
	}
	 
	@Override
	public Boolean delete(int id) {
		Optional<Frasi> foundFrase = frasiRepository.findById(id);
		
		if(foundFrase.isEmpty()) {
			return false;
		}
		
		frasiRepository.delete(foundFrase.get());
		
		return true;
	}
	
}
