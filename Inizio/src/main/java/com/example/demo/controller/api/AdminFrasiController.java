package com.example.demo.controller.api;

import java.awt.event.ItemEvent;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Frasi;

@RestController
public class AdminFrasiController {
	
	private List<Frasi> list;
	private int lastId; 

	public AdminFrasiController() {
		list = new ArrayList<Frasi>();
		
		list.add(new Frasi(0, "Ciao"));
		list.add(new Frasi(1, "Ciao 2"));
		list.add(new Frasi(2, "Ciao 3"));
		list.add(new Frasi(3, "Ciao 4"));
		
		Frasi lastIndex = list.get(list.size()-1);
		lastId = lastIndex.getId();
	}
	
	@RequestMapping("/admin/api/frasi")
	public Iterable<Frasi> getAll(){
		return list;
	}
	
	// {id} per specificare che il valore e variabile 
	@RequestMapping("/admin/api/frasi/{id}")
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
	
	// Aggiungere una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.POST
	@RequestMapping(value="/admin/api/frasi", method = RequestMethod.POST)
	public Frasi create(@RequestBody Frasi frase) {
		// Prende l'ultimo id della lista e lo incrementa di 1
		lastId++;
		// Settare l'id della frase
		frase.setId(lastId);
		
		// Aggiungere alla lista la frase
		list.add(frase);
		return frase;
	}
	
	// Modificare una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.PUT
	// {id} indica un valore variabile grazie a @PathVariable
	@RequestMapping(value="/admin/api/frasi/{id}", method = RequestMethod.PUT)
	public Frasi update(@PathVariable int id,@RequestBody Frasi frase) {
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> foundFrase = list.stream().filter(item->item.getId() == id).findFirst();
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundFrase.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La frase con l'id richiesto non è stata trovata");
		}
		
		// Prende la frase trovata con l'id richiesto, cambia la frase con quella richiesta.
		foundFrase.get().setFrase(frase.getFrase());
		list.set(foundFrase.get().getId(), foundFrase.get());
		
		// Ritorna la frase che è stata modificata
		return list.get(foundFrase.get().getId());
	}
	
	// Eliminare una frase, value = url della richiesta mentre method = il metodo della richiesta in questo caso RequestMethod.DELETE
	// {id} indica un valore variabile grazie a @PathVariable
	@RequestMapping(value="/admin/api/frasi/{id}", method=RequestMethod.DELETE)
	public Frasi delete(@PathVariable int id) {
		// Permette di cercare la singola frase da eliminare
		Optional<Frasi> foundElement = list.stream().filter(item->item.getId() == id).findFirst();
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundElement.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase non trovata");
		}
		
		// Rimuove dalla lista la frase trovata
		list.remove(foundElement.get());
		
		// Ritorna la frase eliminata dalla lista
		return foundElement.get();
	}
}
