package br.com.alura.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.dto.OfertaForm;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta novaOferta(@Valid @RequestBody OfertaForm form) {
		Optional<Pedido> optional = pedidoRepository.findById(form.getIdPedido());
		if (!optional.isPresent()) {
			return null;
		}
		Pedido pedido = optional.get();
		Oferta oferta = form.toOferta();
		pedido.addOferta(oferta);
		pedidoRepository.save(pedido);
		return oferta;
	}

}
