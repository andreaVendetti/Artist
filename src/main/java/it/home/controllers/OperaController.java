package it.home.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.Opera;
import it.home.services.OperaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/opere")
public class OperaController {

	@Autowired
	OperaService serviceO;
	
	@GetMapping
	public Iterable<Opera> getAll(){
		return serviceO.getAll();
	}
	
	
}
