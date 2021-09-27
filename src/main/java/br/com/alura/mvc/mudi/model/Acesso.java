package br.com.alura.mvc.mudi.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acessos")
public class Acesso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uri;
	private LocalDateTime inicio;
	private Duration duracao;
	
	public Acesso() {
	}

	public Acesso(String uri, LocalDateTime inicio) {
		this.uri = uri;
		this.inicio = inicio;
	}

	public String getUri() {
		return uri;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public Duration getDuracao() {
		return duracao;
	}
	
	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}

}
