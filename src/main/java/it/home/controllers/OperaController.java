package it.home.controllers;


import java.io.IOException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.home.models.Opera;
import it.home.services.AuthService;
import it.home.services.CloudinaryService;
import it.home.services.OperaService;

@RestController
@RequestMapping("api/opere")
public class OperaController {

	@Autowired
	OperaService serviceO;

	@Autowired
	AuthService serviceA;
	
	@Autowired
	CloudinaryService serviceC;
	
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
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
	                                 @RequestHeader("x-utente-id") int idU) {
	    if (!verify(idU))
	        return ResponseEntity.status(403).body("Non autorizzato");
	    try {
	        String url = serviceC.upload(file);
	        return ResponseEntity.ok(Map.of("url", url));
	    } catch (IOException e) {
	        return ResponseEntity.status(500).body("Errore caricamento immagine");
	    }
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
