package it.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.Utente;
import it.home.services.AuthService;
import it.home.services.UtenteService;

@RestController
@RequestMapping("api/utenti")
public class UtenteController {

	@Autowired
	UtenteService serviceU;

	@Autowired
	AuthService authService;
	
	// il "?" serve per indicare che può arrivare un ogetto o un messaggio di errore
	@GetMapping
	public ResponseEntity<?> getAll(@RequestHeader("x-utente-id") int utenteId) {
		if (!authService.isAdmin(utenteId))
			return ResponseEntity.status(403).body("Non autorizzato");
		return ResponseEntity.ok(serviceU.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable int id, @RequestHeader("x-utente-id") int utenteId) {
		if (!authService.isAdmin(utenteId))
			return ResponseEntity.status(403).body("Non autorizzato");
		return ResponseEntity.ok(serviceU.getOne(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Utente u, @RequestHeader("x-utente-id") int utenteId) {
		if (!authService.isAdmin(utenteId))
			return ResponseEntity.status(403).body("Non autorizzato");
		return ResponseEntity.ok(serviceU.save(u));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader("x-utente-id") int utenteId) {
		if (!authService.isAdmin(utenteId))
			return ResponseEntity.status(403).body("Non autorizzato");
		serviceU.delete(id);
		return ResponseEntity.ok().build();
	}

}
