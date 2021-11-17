package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusOferta;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.OfertaRepository;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller()
@RequestMapping("/oferta")
public class OfertaController {
	
	@Autowired
	private OfertaRepository repository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	private String home() {
		return "oferta/home";
	}
	
	@GetMapping("/aprovar/{id}")
	public String aprovarOferta(@PathVariable String id) {
		
		Oferta oferta = repository.getById(Integer.valueOf(id));
		oferta.setStatus(StatusOferta.APROVADA);
		
		Pedido pedido = oferta.getPedido();
		pedido.setStatus(StatusPedido.APROVADO);
		pedido.setValor(oferta.getValor());
		pedido.setDataEntrega(oferta.getDataEntrega());
		pedidoRepository.save(pedido);
		
		return "redirect:/usuario/pedidos/aprovado";
	}
	
	@GetMapping("/recusar/{id}")
	public String recusarOferta(@PathVariable String id) {
		
		Oferta oferta = repository.getById(Integer.valueOf(id));
		oferta.setStatus(StatusOferta.RECUSADA);
		repository.save(oferta);
		
		return String.format("redirect:/pedido/%s/ofertas", id);
	}
	
}
