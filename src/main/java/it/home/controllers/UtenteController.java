package it.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.Utente;
import it.home.services.UtenteService;

@RestController
@RequestMapping("api/utenti")
public class UtenteController {

	@Autowired
	UtenteService serviceU;

	@GetMapping
	public Iterable<Utente> getAll() {
		return serviceU.getAll();
	}

	@GetMapping("/{id}")
	public Utente get(@PathVariable int id) {
		return serviceU.getOne(id);
	}

	@PostMapping
	public Utente save(@RequestBody Utente u) {
		return serviceU.save(u);
	}
}
