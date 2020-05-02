package com.eventosAPP.repository;

import org.springframework.data.repository.CrudRepository;
import com.eventosAPP.model.Convidado;
import com.eventosAPP.model.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
		Iterable<Convidado> findByEvento(Evento evento);

	Convidado findByRg(String rg);
}
