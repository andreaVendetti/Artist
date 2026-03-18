package it.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.home.models.Utente;
import it.home.repositories.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository repo;
	
	public Utente save(Utente u) {
		return repo.save(u);
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
