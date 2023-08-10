package com.example.demo.controller.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Frasi;

@RestController
public class FrasiController {
	
	private List<Frasi> list;
	
	public FrasiController() {
		list = new ArrayList<Frasi>();
		
		list.add(new Frasi(0, "Ciao"));
		list.add(new Frasi(1, "Ciao 2"));
		list.add(new Frasi(2, "Ciao 3"));
		list.add(new Frasi(3, "Ciao 4"));
	}

	@RequestMapping("/api/frasi")
	public Iterable<Frasi> getAll() {
		return list;
	}
	
	// {id} per specificare che il valore e variabile 
	@RequestMapping("/api/frasi/{id}")
	public Frasi getById(@PathVariable int id) { // PathVariable permette di prendere il valore di {id} nel url
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> frase = list.stream().filter(item->item.getId() == id).findFirst();
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (frase.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase non trovata");
		}
		
		// Ritorna la frase che hai trovato con l'id richiesto
		return frase.get();
	}
		
}
