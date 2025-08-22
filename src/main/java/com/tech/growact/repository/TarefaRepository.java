package com.tech.growact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.growact.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	  @Query("select count(t) from Tarefa t " +
	           "where t.objetivo.usuario.id = :usuarioId and t.concluido = true")
	    long countConcluidasByUsuario(@Param("usuarioId") Long usuarioId);

	    @Query("select count(t) from Tarefa t " +
	           "where t.objetivo.usuario.id = :usuarioId and (t.concluido = false or t.concluido is null)")
	    long countPendentesByUsuario(@Param("usuarioId") Long usuarioId);
	List<Tarefa> findByObjetivoId(Long objetivoId);

}
