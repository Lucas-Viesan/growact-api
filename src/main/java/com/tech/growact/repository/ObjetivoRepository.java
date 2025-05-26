package com.tech.growact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.growact.model.Objetivo;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
	
}
