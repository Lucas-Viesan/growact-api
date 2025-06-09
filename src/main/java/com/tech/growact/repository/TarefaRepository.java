package com.tech.growact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.growact.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	List<Tarefa> findByObjetivoId(Long objetivoId);

}
