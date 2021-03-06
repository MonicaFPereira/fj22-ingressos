package br.com.caelum.ingressos.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala){
		this.sessoesDaSala = sessoesDaSala;
	}
	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova){
		
		LocalDate hoje = LocalDate.now();
		
		LocalDateTime horarioExistente = sessaoExistente.getHorario().atDate(hoje);
		LocalDateTime horarioNova = sessaoNova.getHorario().atDate(hoje);
		
		boolean terminaAntes = sessaoNova.getHorarioTermino().atDate(hoje)
				.isBefore(horarioExistente);
		
		boolean comecaDepois = sessaoExistente.getHorarioTermino().atDate(hoje).isBefore(horarioNova);
		
		if (terminaAntes||comecaDepois){
			return false;
		}
		return true;
		
	}
	
	public boolean cabe(Sessao sessaoNova){
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> horarioIsConflitante(sessaoExistente, sessaoNova));
	}
}
