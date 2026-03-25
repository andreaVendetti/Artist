package it.home.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.LoginRequest;
import it.home.models.Utente;
import it.home.services.UtenteService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	UtenteService service;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest req) {
		try {
			Utente u = service.login(req.getEmail(), req.getPassword());
			return ResponseEntity.ok(Map.of(
						"id", u.getId(),
						"nome", u.getNome(),
						"cognome", u.getCognome(),
						"email", u.getEmail(),
						"pass", u.getPass(),
						"admin", u.getAdmin()
					));
		} catch (RuntimeException e) {
			 return ResponseEntity.status(401).body(Map.of("errore", e.getMessage()));
		}
	}
}
