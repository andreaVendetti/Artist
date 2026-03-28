package it.home.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.LoginRequest;
import it.home.models.Utente;
import it.home.services.JwtService;
import it.home.services.UtenteService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	UtenteService service;

	@Autowired
	JwtService serviceJ;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest req) {
		try {
			
			Utente u = service.login(req.getEmail(), req.getPassword());
			String token = serviceJ.generateToken(u.getId());
			return ResponseEntity.ok(Map.of(
						"id", u.getId(),
						"nome", u.getNome(),
						"cognome", u.getCognome(),
						"email", u.getEmail(),
						"admin", u.getAdmin(),
						"token", token
					));
		} catch (RuntimeException e) {
			 return ResponseEntity.status(401).body(Map.of("errore", e.getMessage()));
		}
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> validate(@RequestHeader("Authorization") String authHeader){
		String token = authHeader.replace("Bearer ", "");
	    if (serviceJ.isValid(token)) {
	        return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.status(401).build();
	}
}
