package br.com.alura.mvc.mudi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.mvc.mudi.interceptor.AcessoInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Bean
	public AcessoInterceptor getAcessoInterceptor() {
		return new AcessoInterceptor();
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getAcessoInterceptor())
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/acessos");
	}

}
