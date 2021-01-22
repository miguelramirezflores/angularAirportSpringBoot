package com.angularAirport.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.angularAirport.jtsecurity.AutenticadorJWT;
import com.angularAirport.model.Nacionalidad;
import com.angularAirport.model.Usuario;
import com.angularAirport.repositories.NacionalidadRepository;
import com.angularAirport.repositories.TipoSexoRepository;
import com.angularAirport.repositories.UsuarioRepository;


@CrossOrigin
@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository usurep;
	@Autowired
	NacionalidadRepository nacionalidadrep;
	@Autowired
	TipoSexoRepository sexorep;
	
	@GetMapping("usuario/getAutenticado")
	public DTO getUsuarioAutenticado(boolean img , HttpServletRequest request) {
		
		
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		Usuario usu = usurep.findById(idUsuarioAutenticado).get();
		
		
		
		return getDTOFromUsuario(usu, img) ;
	}
	
	
	
	
	
	
	//metodos post =======================================================
	
	
	
	@PostMapping("/usuario/autentica")
	public DTO autenticaUsuario(@RequestBody DatosAutenticacionUsuario datos) {
		DTO dto =  new DTO();
		
		Usuario usuarioAutenticado = usurep.findByUsuarioAndPassword(datos.usuario, datos.password);
		if(usuarioAutenticado!=null) {
			dto.put("jwt", AutenticadorJWT.codificaJWT(usuarioAutenticado));
		}
		
		
		
		return dto;
	}
	
	
	
	@PostMapping("/usuario/ratificaPassword")
	public DTO ratificaPassword(@RequestBody DTO dtoRecibido , HttpServletRequest request) {
		DTO dto = new DTO();
		
		dto.put("result", "fail");
		
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		
		try {
			Usuario usuarioAutenticado = usurep.findById(idUsuarioAutenticado).get();
			String Password = (String) dtoRecibido.get("password");
			if (Password.equals(usuarioAutenticado.getPassword())) {
				dto.put("result", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dto;
	}
	
	
	
	@PostMapping("/usuario/modificarPassword")
	public DTO modificaPassword (@RequestBody DTO dtoRecibido , HttpServletRequest  request) {
		DTO dto = new DTO();
		
		dto.put("result","fail");
		
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		
		try {
			Usuario usuariioAutenticadoUsuario =  usurep.findById(idUsuarioAutenticado).get();
			String password = (String) dtoRecibido.get("password");
			usuariioAutenticadoUsuario.setPassword(password);
			usurep.save(usuariioAutenticadoUsuario);
			dto.put("result", "ok");
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dto;
		
	}
	
	
	
	@PostMapping("/usuario/update")
	public DTO modificaDatosUsuario(@RequestBody DTO dtoRecibido, HttpServletRequest request) {
		DTO dto = new DTO();
		
		dto.put("result", "fail");
		
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		
		try {
			Usuario usuarioAutenticado = usurep.findById(idUsuarioAutenticado).get();
			
			usuarioAutenticado.setUsuario((String) dtoRecibido.get("usuario"));
			usuarioAutenticado.setEmail((String) dtoRecibido.get("email"));
			usuarioAutenticado.setNombre((String) dtoRecibido.get("nombre"));
			usuarioAutenticado.setFechaNav(new Date((long)dtoRecibido.get("fechaNacimiento")));
			usuarioAutenticado.setNacionalidad(this.nacionalidadrep.findById((int) dtoRecibido.get("nacionalidad")).get());
			usuarioAutenticado.setTiposexo(this.sexorep.findById((int) dtoRecibido.get("sexo")).get());
			usuarioAutenticado.setImagen(Base64.decodeBase64((String) dtoRecibido.get("imagen")));
			usurep.save(usuarioAutenticado);
			dto.put("result", "ok");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return dto;
	}
	
	
	/**
	 * 
	 * @param usu
	 * @param incluirImagen
	 * @return
	 */
	private DTO getDTOFromUsuario (Usuario usu, boolean incluirImagen) {
		DTO dto = new DTO(); // Voy a devolver un dto
		if (usu != null) {
			dto.put("id", usu.getId());
			dto.put("nombre", usu.getNombre());
			dto.put("usuario", usu.getUsuario());
			dto.put("password", usu.getPassword());
			dto.put("email", usu.getEmail());
			dto.put("fechaNacimiento", usu.getFechaNav());
			dto.put("fechaEliminacion", usu.getFechaEliminacion());
			dto.put("nacionalidad", usu.getNacionalidad().getId());
			dto.put("sexo", usu.getTiposexo().getId());
			dto.put("imagen", incluirImagen? usu.getImagen() : "");
		}
		return dto;
	}
	
}

class DatosAutenticacionUsuario{
	String usuario;
	String password;
	public DatosAutenticacionUsuario(String usuario,String password) {
		super();
		this.usuario=usuario;
		this.password=password;
	}
}
