package com.angularAirport.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularAirport.jtsecurity.AutenticadorJWT;
import com.angularAirport.model.Vuelo;
import com.angularAirport.repositories.UsuarioRepository;
import com.angularAirport.repositories.VueloRepository;

@CrossOrigin
@RestController
public class VueloController {

	@Autowired  VueloRepository vuelosRec;
	@Autowired  UsuarioRepository usuRec;

	
	
	@GetMapping("/vuelos/listado")
	public DTO vuelosReservadosPorElUsuario(int pagina ,int vuelosPorPagina, HttpServletRequest request) {
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		DTO dto =  new DTO();
		dto.put("result", "fail");
		List<DTO> listadoVuelosDto = new ArrayList<DTO>();
		long totalVuelos = 0;
		
		try {
			
			List<Vuelo> vuelos = new ArrayList<Vuelo>();
			
			vuelos =  this.vuelosRec.getVuelosPaginados(pagina, vuelosPorPagina);
			
			totalVuelos =  this.vuelosRec.countVuelos();
			
			for (Vuelo vuelo : vuelos) {
				listadoVuelosDto.add(getDtoFromVuelo(vuelo));
			}
			
			
			dto.put("result", "ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		dto.put("listadoVuelos", listadoVuelosDto);
		dto.put("totalVuelos", totalVuelos);
		return dto;
	}
	
	
	private DTO getDtoFromVuelo(Vuelo v) {
		
		DTO dto =  new DTO();
		
		dto.put("id", v.getId() );
		dto.put("origen", v.getNacionalidad1());
		dto.put("destino", v.getNacionalidad2());
		dto.put("precio", v.getPrecio());
		dto.put("fecha", v.getFecha());
		return dto;
	}
}
