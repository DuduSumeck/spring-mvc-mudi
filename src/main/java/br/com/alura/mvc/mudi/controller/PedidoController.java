package br.com.alura.mvc.mudi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.model.dto.PedidoForm;
import br.com.alura.mvc.mudi.repository.OfertaRepository;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form(PedidoForm form) {
		return "pedido/form";
	}
	
	@PostMapping("/novo")
	public String novo(@Valid PedidoForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form);
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		Pedido pedido = form.toPedido();
		user.addPedido(pedido);
		repository.save(pedido);
		return "redirect:/usuario/pedidos";
	}
	
	@GetMapping("/excluir/{id}")
	public String remove(@PathVariable Integer id) {
		repository.deleteById(id);
		return "redirect:/usuario/pedidos";
	}
	
	@GetMapping("/{id}/ofertas")
	public String buscarOfertas(@PathVariable String id, Model model) {
		Pedido pedido = repository.getById(Integer.valueOf(id));
		List<Oferta> ofertas = ofertaRepository.findAllByPedido(pedido);
		model.addAttribute("pedido", pedido);
		model.addAttribute("ofertas", ofertas);
		return "pedido/ofertas";
	}

}
