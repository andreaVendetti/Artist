package it.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.home.models.Utente;
import it.home.repositories.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository repo;

	public Utente save(Utente u) {
	    if(u.getPass() != null && !u.getPass().isEmpty()) {
	        String pass = BCrypt.withDefaults().hashToString(12, u.getPass().toCharArray());
	        u.setPass(pass);
	    } else {
	        // se la pass è vuota, mantieni quella già nel DB
	        Utente esistente = repo.findById(u.getId()).get();
	        u.setPass(esistente.getPass());
	    }
	    return repo.save(u);
	}
	
	
	public Utente login(String email, String password) {
		Utente u = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Utente non trovato"));
		// gli si passa prima la pass presa dal frontend e poi quella del db e le confronta
	
		if(BCrypt.verifyer().verify(password.toCharArray(), u.getPass()).verified == true) {
			
			return u;
		}
		throw new RuntimeException("Password errata");
	}
	
	public Iterable<Utente> getAll(){
		return repo.findAll();
	}

	public Utente getOne(int id){
		return repo.findById(id).get();
	}

	public void delete(int id){
		repo.deleteById(id);
	}
}
