package it.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.home.services.OperaService;

@RestController
public class OperaController {

	@Autowired
	OperaService serviceO;
	
	
	
	
}
