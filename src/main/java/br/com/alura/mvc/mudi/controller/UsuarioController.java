package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.model.dto.UsuarioForm;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/pedidos")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "usuario/home";
	}
	
	@GetMapping("/pedidos/{status}")
	public String buscaPorStatus(@PathVariable String status, Principal principal, Model model) {
		List<Pedido> pedidos = pedidoRepository.findAllByUserAndStatus(principal.getName(),
				StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
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

}
