package com.angularAirport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angularAirport.model.Billete;
@Repository
public interface BillereRepository extends CrudRepository<Billete, Integer> {

	@Query(value = "select * from billete where usuario_idusuario = ?",nativeQuery = true)
	public List<Billete> getBilletesUsuario(int idUsuario);
}
