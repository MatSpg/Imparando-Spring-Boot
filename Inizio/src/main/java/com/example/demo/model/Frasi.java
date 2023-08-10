package com.example.demo.model;

public class Frasi {

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
