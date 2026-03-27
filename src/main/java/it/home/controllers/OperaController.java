package it.home.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.home.models.Opera;
import it.home.services.AuthService;
import it.home.services.OperaService;

@RestController
@RequestMapping("api/opere")
public class OperaController {

	@Autowired
	OperaService serviceO;

	@Autowired
	AuthService serviceA;
	
	@GetMapping
	public Iterable<Opera> getAll(){
		return serviceO.getAll();
	}

	@GetMapping("/{id}")
	public Opera getOpera(@PathVariable int id) {
		return serviceO.getOpera(id);
	}
	
	public boolean verify(int idU) {
		return (serviceA.isAdmin(idU)) ? true : false;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Opera op, @RequestHeader("x-utente-id") int idU){
		if(verify(idU)) {
			return ResponseEntity.ok(serviceO.save(op));
		} 
		return ResponseEntity.status(403).body("Non autorizzato");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestHeader("x-utente-id") int idU, @RequestBody Opera op){
		if (verify(idU)) {
			return ResponseEntity.ok(serviceO.save(op));
		}
		return ResponseEntity.status(403).body("Non autorizzato");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader("x-utente-id") int idU, @PathVariable int idO){
		if(verify(idU)) {
			serviceO.delete(idO);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(403).body("Non autorizzato");
	}
}
