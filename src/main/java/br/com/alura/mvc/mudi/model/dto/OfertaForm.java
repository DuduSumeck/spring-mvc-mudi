package br.com.alura.mvc.mudi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class OfertaForm {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Integer idPedido;
	
	@NotNull(message = "Informe o valor do produto")
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$", message = "Formato inválido")
	private String valor;
	
	@NotNull(message = "Informa a data de entrega")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}$", message = "Formato inválido")
	private String dataEntrega;
	
	private String comentario;
	
	public OfertaForm() {
	}

	public OfertaForm(Integer idPedido, String valor, String dataEntrega, String comentario) {
		this.idPedido = idPedido;
		this.valor = valor;
		this.dataEntrega = dataEntrega;
		this.comentario = comentario;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public String getValor() {
		return valor;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}
	
	public Oferta toOferta() {
		return new Oferta(new BigDecimal(valor), LocalDate.parse(dataEntrega, FORMATTER), comentario);
	}

}
