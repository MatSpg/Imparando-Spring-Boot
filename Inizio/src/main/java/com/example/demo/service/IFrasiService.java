package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Frasi;

// Questa interfaccia contiene i metodi per i service simili (in questo caso FrasiService e DbFrasiService)
public interface IFrasiService {

	 public Iterable<Frasi> getAll();
	 
	 public Optional<Frasi> getById(int id);
	 
	 public Frasi create(Frasi frase);
	 
	 public Optional<Frasi> update(int id, String frase);
	 
	 public Boolean delete(int id);
	
}
