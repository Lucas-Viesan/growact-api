package com.tech.growact.Dto;



import java.util.List;
import com.tech.growact.model.Objetivo;

public class PerfilResumoDto {
		private String foto;
		private String nome;
		private String email;
		private long totalObjetivosConcluidos;
		private long totalObjetivosPendentes;
		private long totalTarefasConcluidas;
		private long totalTarefasPendentes;
		
		private List<Objetivo> objetivosConcluidos;
		
		
		public PerfilResumoDto(String foto, String nome, String email, long totalObjetivosConcluidos, long totalObjetivosPendentes,   long totalTarefasConcluidas, long totalTarefasPendentes,
                List<Objetivo> objetivosConcluidos, List<Objetivo> objetivosPendentes ) {
			this.foto = foto;
			this.nome = nome;
			this.email = email;
	        this.totalObjetivosConcluidos = totalObjetivosConcluidos;
	        this.totalObjetivosPendentes = totalObjetivosPendentes;
	        this.totalTarefasConcluidas = totalTarefasConcluidas;
	        this.totalTarefasPendentes = totalTarefasPendentes;
	        this.objetivosConcluidos = objetivosConcluidos;
		}
		
		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public long getTotalObjetivosConcluidos() {
			return totalObjetivosConcluidos;
		}


		public void setTotalObjetivosConcluidos(long totalObjetivosConcluidos) {
			this.totalObjetivosConcluidos = totalObjetivosConcluidos;
		}


		public long getTotalObjetivosPendentes() {
			return totalObjetivosPendentes;
		}


		public void setTotalObjetivosPendentes(long totalObjetivosPendentes) {
			this.totalObjetivosPendentes = totalObjetivosPendentes;
		}


		public long getTotalTarefasConcluidas() {
			return totalTarefasConcluidas;
		}


		public void setTotalTarefasConcluidas(long totalTarefasConcluidas) {
			this.totalTarefasConcluidas = totalTarefasConcluidas;
		}


		public long getTotalTarefasPendentes() {
			return totalTarefasPendentes;
		}


		public void setTotalTarefasPendentes(long totalTarefasPendentes) {
			this.totalTarefasPendentes = totalTarefasPendentes;
		}


		public List<Objetivo> getObjetivosConcluidos() {
			return objetivosConcluidos;
		}


		public void setObjetivosConcluidos(List<Objetivo> objetivosConcluidos) {
			this.objetivosConcluidos = objetivosConcluidos;
		}


		
		
}
