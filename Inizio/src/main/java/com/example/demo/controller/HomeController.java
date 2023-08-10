package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Indichiamo a Spring che questo e un Controller
@Controller
public class HomeController {
	
	// Aggiungere un costruttore vuoto in caso di errore 15420 org.thymeleaf.TemplateEngine
	// questo errore non permette di visualizzare la pagina html richiesta
	public HomeController() {}

	// Permette di gestire le richieste che arrivano al path indicato
	@RequestMapping("/")
	public String index() {
		// In questo caso verrà richiesta una pagina html (index.html)
		return "index";
	}
	
	@RequestMapping("/demo")
	// Grazie a @ResponseBody indichiamo che non si vuole caricare una pagina html, ma del contenuto scritto all'interno del return
	@ResponseBody
	public String demo() {
		return "<h1>Demo Page</h1>";
	}
}
