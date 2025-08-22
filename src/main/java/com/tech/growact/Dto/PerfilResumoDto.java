package com.tech.growact.Dto;



import java.util.List;
import com.tech.growact.model.Objetivo;

public class PerfilResumoDto {
		private long totalObjetivosConcluidos;
		private long totalObjetivosPendentes;
		private long totalTarefasConcluidas;
		private long totalTarefasPendentes;
		
		private List<Objetivo> objetivosConcluidos;
		
		
		public PerfilResumoDto(long totalObjetivosConcluidos, long totalObjetivosPendentes,   long totalTarefasConcluidas, long totalTarefasPendentes,
                List<Objetivo> objetivosConcluidos, List<Objetivo> objetivosPendentes ) {
	        this.totalObjetivosConcluidos = totalObjetivosConcluidos;
	        this.totalObjetivosPendentes = totalObjetivosPendentes;
	        this.totalTarefasConcluidas = totalTarefasConcluidas;
	        this.totalTarefasPendentes = totalTarefasPendentes;
	        this.objetivosConcluidos = objetivosConcluidos;
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
