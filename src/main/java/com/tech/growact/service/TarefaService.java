package com.tech.growact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.growact.model.Objetivo;
import com.tech.growact.model.Tarefa;
import com.tech.growact.repository.ObjetivoRepository;
import com.tech.growact.repository.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired 
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	
	public List<Tarefa> listarTarefasPorObjetivo(Long objetivoId) {
	    Objetivo objetivo = objetivoRepository.findById(objetivoId)
	            .orElseThrow(() -> new RuntimeException("Objetivo não encontrado"));

	    // Atualiza progresso
	    atualizarProgresso(objetivo);

	    // Retorna tarefas
	    return tarefaRepository.findByObjetivoId(objetivoId);
	}
	
		
	public Tarefa postTarefa(Tarefa tarefa, Long objetivoId) {
	    Objetivo objetivo = objetivoRepository.findById(objetivoId)
	            .orElseThrow(() -> new RuntimeException("Objetivo não encontrado"));

	    tarefa.setObjetivo(objetivo);
	    atualizarProgresso(tarefa.getObjetivo());
	    return tarefaRepository.save(tarefa);
	    
	}
	
	public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
	    Tarefa tarefa = tarefaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

	    tarefa.setDescricao(tarefaAtualizada.getDescricao());
	    atualizarProgresso(tarefa.getObjetivo());
	    return tarefaRepository.save(tarefa);
	}

	    
	    
	 public Tarefa concluirTarefa(Long tarefaId) {
	        Tarefa tarefa = tarefaRepository.findById(tarefaId)
	                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

	        // Marca a tarefa como concluída
	        tarefa.setConcluido(true);
	        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);

	        // Atualiza o progresso do objetivo
	        atualizarProgresso(tarefa.getObjetivo());

	        return tarefaAtualizada;
	    }

	   
	 public void atualizarProgresso(Objetivo objetivo) {
	        List<Tarefa> tarefas = tarefaRepository.findByObjetivoId(objetivo.getId());

	        if (tarefas.isEmpty()) {
	            objetivo.setPercentual(null);
	        } else {
	            long total = tarefas.size();
	            long concluidas = tarefas.stream().filter(t -> Boolean.TRUE.equals(t.getConcluido())).count();

	            double percentual = (double) concluidas / total * 100.0;
	            objetivo.setPercentual(percentual);

	            // Se 100%, arquiva/conclui o objetivo
	            if (percentual >= 100.0) {
	                objetivo.setConcluido(true); // ou setConcluido(true);
	            }

	            objetivoRepository.save(objetivo);
	        }
	    
	}

}
