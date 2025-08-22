package com.tech.growact.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tech.growact.Dto.PerfilResumoDto;
import com.tech.growact.model.Objetivo;
import com.tech.growact.model.Usuario;
import com.tech.growact.model.UsuarioLogin;
import com.tech.growact.repository.ObjetivoRepository;
import com.tech.growact.repository.TarefaRepository;
import com.tech.growact.repository.UsuarioRepository;
import com.tech.growact.security.JwtService;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private ObjetivoRepository objetivoRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));
	
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());

			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));
			
		}

		return Optional.empty();
	
	}	

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        
        // Gera o Objeto de autenticação
		var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha());
		
        // Autentica o Usuario
		Authentication authentication = authenticationManager.authenticate(credenciais);
        
        // Se a autenticação foi efetuada com sucesso
		if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
			Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

            // Se o usuário foi encontrado
			if (usuario.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados 
			    usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getEmail()));
                usuarioLogin.get().setSenha("");
				
                 // Retorna o Objeto preenchido
			   return usuarioLogin;
			
			}

        } 
            
		return Optional.empty();

    }

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}

	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}

	
	//Lógica dos dados retornados na pagina de perfil do usuario
	public PerfilResumoDto getResumoPerfil(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		List<Objetivo> objetivos = objetivoRepository.findByUsuarioId(usuarioId);

		long totalObjetivosConcluidos = objetivos.stream()
		    .filter(o -> o.getTarefa().stream().allMatch(t -> t.getConcluido() != null && t.getConcluido()))
		    .count();

		long totalObjetivosPendentes = objetivos.size() - totalObjetivosConcluidos;
		
		long totalTarefasConcluidas = tarefaRepository.countConcluidasByUsuario(usuarioId);
		long totalTarefasPendentes = tarefaRepository.countPendentesByUsuario(usuarioId);


        // Se quiser devolver a lista de objetivos concluídos para o front
		List<Objetivo> objetivosConcluidos = objetivos.stream()
			    .filter(o -> o.getTarefa().stream().allMatch(t -> t.getConcluido() != null && t.getConcluido()))
			    .collect(Collectors.toList());

        return new PerfilResumoDto(
                totalObjetivosConcluidos,
                totalObjetivosPendentes,
                totalTarefasConcluidas,
                totalTarefasPendentes,
                objetivosConcluidos,
                null // ou liste também os pendentes se precisar
        );
    }

}