package com.angularAirport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angularAirport.model.Nacionalidad;
@Repository
public interface NacionalidadRepository extends CrudRepository<Nacionalidad, Integer> {
	
	
	@Query(value = "SELECT * FROM nacionalidad where descripcion like ? ", nativeQuery = true)
	public List<Nacionalidad> filterByDescripcion(String filtroDes);
}
