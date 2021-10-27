package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusOferta;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.model.dto.UsuarioForm;
import br.com.alura.mvc.mudi.repository.OfertaRepository;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@GetMapping("/pedidos")
	public String buscarPedidos(Model model, Principal principal, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 3);
		Page<Pedido> pagePedido = pedidoRepository.findAllByUser(principal.getName(), pageable);
		model.addAttribute("pagePedido", pagePedido);
		return "usuario/home";
	}
	
	@GetMapping("/pedidos/{status}")
	public String buscaPorStatus(@PathVariable String status, Principal principal, Model model,
			@RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 3);
		Page<Pedido> pagePedido = pedidoRepository.findAllByUserAndStatus(principal.getName(),
				StatusPedido.valueOf(status.toUpperCase()), pageable);
		model.addAttribute("pagePedido", pagePedido);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/pedidos";
	}
	
	@GetMapping("/form")
	public String form(UsuarioForm form) {
		return "usuario/form";
	}
	
	@PostMapping("/add")
	public String add(@Valid UsuarioForm form, BindingResult result) {
		User user = repository.findByUsername(form.getUsername());
		if (user != null) {
			result.addError(new FieldError("usuarioForm", "username", "Nome de usuário indisponível"));
		}
		if (result.hasErrors()) {
			return form(form);
		}
		repository.save(form.toUser());
		return "redirect:/login";
	}
	
	@GetMapping("/ofertas")
	public String buscarOfertas(Model model, Principal principal, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 3);
		Page<Oferta> pageOferta = ofertaRepository.findAllByUser(principal.getName(), pageable);
		model.addAttribute("pageOferta", pageOferta);
		return "usuario/ofertas";
	}
	
	@GetMapping("/ofertas/{status}")
	public String buscaOfertaPorStatus(@PathVariable String status, Principal principal, Model model,
			@RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 3);
		Page<Oferta> pageOferta = ofertaRepository.findAllByUserAndStatus(principal.getName(),
				StatusOferta.valueOf(status.toUpperCase()), pageable);
		model.addAttribute("pageOferta", pageOferta);
		model.addAttribute("status", status);
		return "usuario/ofertas";
	}
}
