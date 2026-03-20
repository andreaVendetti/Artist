package it.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.home.models.Opera;
import it.home.repositories.OperaRepository;

@Service
public class OperaService {

	@Autowired
	OperaRepository repo;

	public void save(Opera op) {
		repo.save(op);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public Iterable<Opera> getAll(){
		return repo.findAll();
	}

	public Opera getOpera(int id) {
		return repo.findById(id).get();
	}
}
