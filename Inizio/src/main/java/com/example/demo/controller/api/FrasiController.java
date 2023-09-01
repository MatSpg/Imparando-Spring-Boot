package com.example.demo.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Frasi;
import com.example.demo.service.FrasiService;
import com.example.demo.service.IFrasiService;

@RestController
public class FrasiController {
	
	// Dependency Injection
	@Autowired
	@Qualifier("dbFrasiService")
	private IFrasiService frasiService;
	
	public FrasiController() {
		frasiService = new FrasiService();
	}

	@RequestMapping("/api/frasi")
	public Iterable<Frasi> getAll() {
		return frasiService.getAll();
	}
	
	// {id} per specificare che il valore e variabile 
	@RequestMapping("/api/frasi/{id}")
	public Frasi getById(@PathVariable int id) { // PathVariable permette di prendere il valore di {id} nel url
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> frase = frasiService.getById(id);
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (frase.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase non trovata");
		}
		
		// Ritorna la frase che hai trovato con l'id richiesto
		return frase.get();
	}
		
}
