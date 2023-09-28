package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Frasi {

	@Id
	// Per indicare che questo valore si auto incrementa
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Questo campo non può essere vuoto")
	//@Length(min = 3, message = "La frase inserita e troppo corta")
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
