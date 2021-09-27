package br.com.alura.mvc.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.alura.mvc.mudi.model.Acesso;
import br.com.alura.mvc.mudi.repository.AcessoRepository;

public class AcessoInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AcessoRepository repository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		Acesso acesso = new Acesso(uri, LocalDateTime.now());
		request.setAttribute("acesso", acesso);
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getInicio(), LocalDateTime.now()));
		repository.save(acesso);
	}

}
