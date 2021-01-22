package com.angularAirport.jtsecurity;

import java.security.Key;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.StaticApplicationContext;

import com.angularAirport.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class AutenticadorJWT {

	private static Key key = null;
	
	
	public static String codificaJWT(Usuario u) {
		String jws = Jwts.builder().setSubject(""+u.getId()).signWith(SignatureAlgorithm.HS512, getGeneratedKey()).compact();
		
		return jws;
	}
	
	
	/**
	 * 
	 * @param jwt
	 * @return
	 */
	
	public static int getIdUsuarioDesdeJwt(String jwt) {
		try {
			String StringIdUsuario = Jwts.parser().setSigningKey(getGeneratedKey()).parseClaimsJws(jwt).getBody().getSubject();
			int idUsuario= Integer.parseInt(StringIdUsuario);
			return idUsuario;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public  static int getIdUsuarioDesdeJwtIncrustadoEnRequest(HttpServletRequest request) {
		String autHeader = request.getHeader("Authorization");
		if(autHeader != null && autHeader.length() > 8) {
			String jwt = autHeader.substring(7);
			return getIdUsuarioDesdeJwt(jwt);
		}else {
			return -1;
		}
	}
	
	
	
	
	
	/**
	 * generador de key 
	 * @return
	 */
	private static Key getGeneratedKey() {
		if(key ==null) {
			key = MacProvider.generateKey();
		}
		
		return key;
	}
}
