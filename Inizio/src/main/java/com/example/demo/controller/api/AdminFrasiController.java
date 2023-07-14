package com.example.demo.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@RequestMapping("/admin/api/frasi/{id}")
	public Frasi getById(@PathVariable int id) {
		Optional<Frasi> frase = list.stream().filter(item->item.getId() == id).findFirst();
		
		if (frase.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase non trovata");
		}
		
		return frase.get();
	}
	
	@RequestMapping(value="/admin/api/frasi", method = RequestMethod.POST)
	public Frasi create(@RequestBody Frasi frase) {
		lastId++;
		frase.setId(lastId);
		
		list.add(frase);
		return frase;
	}
}
