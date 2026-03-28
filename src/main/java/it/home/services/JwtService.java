package it.home.services;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long SCADENZA = 1000 * 60 * 60 * 8; // scadenza di 8 ore

	public String generateToken(int idU) {
		return Jwts.builder().setSubject(String.valueOf(idU))// gli passo l'id Utente
				.setIssuedAt(new Date())// avvio la creazione
				.setExpiration(new Date(System.currentTimeMillis() + SCADENZA))// do una scadenza
				.signWith(secretKey) // gli passo la chiave segreta
				.compact(); // lo trasformo in stringa
	}

	public boolean isValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
