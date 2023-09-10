package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Frasi {

	@Id
	// Per indicare che questo valore si auto incrementa
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String frase;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFrase() {
		return frase;
	}
	
	public void setFrase(String frase) {
		this.frase = frase;
	}
	
	public Frasi() {}
	
	public Frasi(int id, String frase) {
		this.id = id;
		this.frase = frase;
	}
	
}
