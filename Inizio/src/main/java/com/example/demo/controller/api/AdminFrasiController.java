package com.example.demo.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Frasi;
import com.example.demo.service.FrasiService;

@RestController
public class AdminFrasiController {
	
	// Dependency Injection
	@Autowired
	private FrasiService frasiService;

	public AdminFrasiController() {}
	
	@RequestMapping("/admin/api/frasi")
	public Iterable<Frasi> getAll(){
		return frasiService.getAll();
	}
	
	// {id} per specificare che il valore e variabile 
	@RequestMapping("/admin/api/frasi/{id}")
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
	
	// Aggiungere una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.POST
	@RequestMapping(value="/admin/api/frasi", method = RequestMethod.POST)
	public Frasi create(@RequestBody Frasi frase) {
		return frasiService.create(frase);
	}
	
	// Modificare una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.PUT
	// {id} indica un valore variabile grazie a @PathVariable
	@RequestMapping(value="/admin/api/frasi/{id}", method = RequestMethod.PUT)
	public Frasi update(@PathVariable int id, @RequestBody Frasi frase) {
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> foundFrase = frasiService.update(id, frase);
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundFrase.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La frase con l'id richiesto non è stata trovata");
		}
		
		// Ritorna la frase che è stata modificata
		return foundFrase.get();
	}
	
	// Eliminare una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.DELETE
	// {id} indica un valore variabile grazie a @PathVariable
	@RequestMapping(value="/admin/api/frasi/{id}", method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id) {
		// Permette di cercare la singola frase da eliminare
		Boolean foundElement = frasiService.delete(id);
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundElement == false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase non trovata");
		}
		
		return foundElement;
	}
}
