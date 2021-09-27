package br.com.alura.mvc.mudi.model.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Pedido;

public class PedidoForm {

	@NotBlank(message = "O campo nome do produto é obrigatório")
	private String nomeProduto;
	@NotBlank(message = "O campo url do produto é obrigatório")
	private String urlProduto;
	@NotBlank(message = "O campo url da imagem é obrigatório")
	private String urlImagem;
	private String descricao;

	public PedidoForm(String nomeProduto, String urlProduto, String urlImagem, String descricao) {
		this.nomeProduto = nomeProduto;
		this.urlProduto = urlProduto;
		this.urlImagem = urlImagem;
		this.descricao = descricao;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pedido toPedido() {
		return new Pedido(nomeProduto, null, descricao, null, urlImagem, urlProduto);
	}

}
