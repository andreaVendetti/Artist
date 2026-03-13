package it.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.home.services.UtenteService;

@RestController
public class UtenteController {

	@Autowired
	UtenteService serviceU;
	
	
}
