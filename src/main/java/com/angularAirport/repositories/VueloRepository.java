package com.angularAirport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angularAirport.model.Vuelo;
@Repository
public interface VueloRepository extends CrudRepository<Vuelo, Integer> {

	@Query(value = "SELECT  * FROM vuelo order by fecha desc limit ?,?", nativeQuery = true)
	public List<Vuelo> getVuelosPaginados(int pagina,int elementosPorPagina);
	
	@Query(value = "SELECT count(v.id) FROM vuelo as v" , nativeQuery = true)
	public long countVuelos();
}
