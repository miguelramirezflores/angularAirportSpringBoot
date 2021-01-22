package com.angularAirport.repositories;

import org.springframework.cglib.transform.impl.InterceptFieldEnabled;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angularAirport.model.TipoSexo;
@Repository
public interface TipoSexoRepository extends CrudRepository<TipoSexo, Integer> {

}
