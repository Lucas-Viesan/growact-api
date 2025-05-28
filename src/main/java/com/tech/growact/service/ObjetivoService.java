package com.tech.growact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.growact.model.Objetivo;
import com.tech.growact.model.Usuario;
import com.tech.growact.repository.ObjetivoRepository;
import com.tech.growact.repository.UsuarioRepository;

@Service
public class ObjetivoService {
	
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    //Método listar objetivos do usuário logado
    public List<Objetivo> buscarObjetivosPorUsuario(Long usuarioId){
    	return objetivoRepository.findByUsuarioId(usuarioId);
    }
    
    
    //Método para criar objetivo associado ao usuário
    public Objetivo postObjetivo (Objetivo objetivo, Long usuarioId) {
    	Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        long objetivosAtivos = objetivoRepository.countByUsuarioIdAndConcluidoFalse(usuarioId);

        if (objetivosAtivos >= 3) {
            throw new RuntimeException("Você só pode ter até 3 objetivos ativos. Conclua ou exclua algum para criar um novo.");
        }

        objetivo.setUsuario(usuario);
        objetivo.setPercentual(0);
        objetivo.setConcluido(false);

        return objetivoRepository.save(objetivo);
    
    }
    
    

}
