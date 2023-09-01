package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Frasi;

// Per dare uno nome specifico al service per richiamarlo attraverso la dipendency injection
@Service("listFrasiService")
public class FrasiService implements IFrasiService {
	
	private List<Frasi> list;
	private int lastId; 

	public FrasiService() {
		list = new ArrayList<Frasi>();
		
		list.add(new Frasi(0, "Ciao"));
		list.add(new Frasi(1, "Ciao 2"));
		list.add(new Frasi(2, "Ciao 3"));
		list.add(new Frasi(3, "Ciao 4"));
		
		Frasi lastIndex = list.get(list.size()-1);
		lastId = lastIndex.getId();
	}
	
	@Override
	public Iterable<Frasi> getAll(){
		return list;
	}
	
	@Override
	public Optional<Frasi> getById(int id) {
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> frase = list.stream().filter(item->item.getId() == id).findFirst();
		
		// Ritorna la frase che hai trovato con l'id richiesto
		return frase;
	}
	
	@Override
	public Frasi create(Frasi frase) {
		// Prende l'ultimo id della lista e lo incrementa di 1
		lastId++;
		// Settare l'id della frase
		frase.setId(lastId);
		
		// Aggiungere alla lista la frase
		list.add(frase);
		
		return frase;
	}

	@Override
	public Optional<Frasi> update(int id, String frase) {
		// Permette di cercare la singola frase da restituire
		Optional<Frasi> foundFrase = list.stream().filter(item->item.getId() == id).findFirst();
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundFrase.isEmpty()) {
			return Optional.empty();
		}
		
		// Prende la frase trovata con l'id richiesto, cambia la frase con quella richiesta.
		foundFrase.get().setFrase(frase);
		
		// Ritorna la frase che è stata modificata
		return foundFrase;
	}
	
	@Override
	public Boolean delete(int id) {
		// Permette di cercare la singola frase da eliminare
		Optional<Frasi> foundElement = list.stream().filter(item->item.getId() == id).findFirst();
		
		// S	e non viene trovata nessuna frase con l'id richiesto lo status della richiesta sarà NOT_FOUND
		if (foundElement.isEmpty()) {
			return false;
		}
		
		// Rimuove dalla lista la frase trovata
		list.remove(foundElement.get());
		
		// Ritorna la frase eliminata dalla lista
		return true;
	}
}
