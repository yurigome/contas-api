package br.com.yuri.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.yuri.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta,UUID> {

	@Query("select c from Conta c order by c.nome")
	List<Conta> findAll();
	
	@Query("select c from Conta c where c.idConta = :param")
	Conta find(@Param ("param")UUID idConta);
	
	
	
}
