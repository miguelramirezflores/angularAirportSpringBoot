package com.angularAirport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angularAirport.model.Usuario;


@Repository
public interface UsuarioRepository extends  CrudRepository<Usuario,Integer> {
	public Usuario findByNombre(String nombre);
	public Usuario findByUsuarioAndPassword(String name, String password);


}
