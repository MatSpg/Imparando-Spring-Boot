package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Indichiamo a Spring che questo e un Controller
@Controller
public class AdminController {
	
	public AdminController() {}
	
	// Permette di gestire le richieste che arrivano al path indicato
	@RequestMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String admin() {
		// In questo caso verrà richiesta una pagina html (admin.html)
		return "admin";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}
