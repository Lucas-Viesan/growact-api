package com.tech.growact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tech.growact.model.Objetivo;
import com.tech.growact.repository.ObjetivoRepository;
import com.tech.growact.service.ObjetivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/objetivos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ObjetivoController {
	
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@GetMapping
	public ResponseEntity <List<Objetivo>> getAll() {
		return ResponseEntity.ok(objetivoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Objetivo> getById(@PathVariable Long id){
		return objetivoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/cadastrar/{usuarioId}")
	 public ResponseEntity<Objetivo> postObjetivo(@RequestBody Objetivo objetivo, @PathVariable Long usuarioId) {
		Objetivo novoObjetivo = objetivoService.postObjetivo(objetivo, usuarioId);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoObjetivo);
}
	
	@PutMapping("/atualizar")
	public ResponseEntity <Objetivo> put(@Valid @RequestBody Objetivo objetivo){
		return objetivoRepository.findById(objetivo.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(objetivoRepository.save(objetivo)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
    	Optional<Objetivo> objetivo = objetivoRepository.findById(id);
    	
    	if(objetivo.isEmpty()) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	} else {
    		objetivoRepository.deleteById(id);
    	}
    }

}
