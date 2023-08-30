package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Indichiamo a Spring che questo e un Controller
@Controller
public class AdminController {
	
	public AdminController() {}
	
	// Permette di gestire le richieste che arrivano al path indicato
	@RequestMapping("/admin")
	public String admin() {
		// In questo caso verrà richiesta una pagina html (admin.html)
		return "admin";
	}
	
}
