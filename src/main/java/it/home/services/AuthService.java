package it.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.home.repositories.UtenteRepository;

@Service
public class AuthService {

	@Autowired
	UtenteRepository repo;

	// se utente con id idU equivale a 1 allora restituisce true
	public boolean isAdmin(int idU) {
		return repo.findById(idU).map(u -> u.getAdmin() == 1).orElse(false);
	}
}
