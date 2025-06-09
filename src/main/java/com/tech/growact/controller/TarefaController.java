package com.tech.growact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.growact.model.Tarefa;
import com.tech.growact.repository.TarefaRepository;
import com.tech.growact.service.TarefaService;

@RestController 
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping("/objetivo/{objetivoId}")
	public ResponseEntity<List<Tarefa>> listarTarefasPorObjetivo(@PathVariable Long objetivoId) {
	    List<Tarefa> tarefas = tarefaRepository.findByObjetivoId(objetivoId);
	    return ResponseEntity.ok(tarefas);
	}

	@PostMapping("/cadastrar/{objetivoId}")
	public ResponseEntity<Tarefa> postTarefa(@PathVariable Long objetivoId, 
	                                           @RequestBody Tarefa tarefa) {
	    Tarefa novaTarefa = tarefaService.postTarefa(tarefa, objetivoId);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id,
	                                               @RequestBody Tarefa tarefaAtualizada) {
	    Tarefa tarefa = tarefaService.atualizarTarefa(id, tarefaAtualizada);
	    return ResponseEntity.ok(tarefa);
	}

	@PutMapping("/concluir/{id}")
	public ResponseEntity<Tarefa> concluirTarefa(@PathVariable Long id) {
	    Tarefa tarefa = tarefaService.concluirTarefa(id);
	    return ResponseEntity.ok(tarefa);
	}

	

}
