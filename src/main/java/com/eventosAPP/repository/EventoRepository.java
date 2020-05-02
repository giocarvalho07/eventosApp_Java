package com.eventosAPP.repository;


import org.springframework.data.repository.CrudRepository;
import com.eventosAPP.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{

	Evento findById(Long id);


}
