package br.com.alura.mvc.mudi.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.mvc.mudi.model.Authority;
import br.com.alura.mvc.mudi.model.User;

public class UsuarioForm {
	
	@NotBlank(message = "O campo nome do usuário é obrigatório")
	@Size(max = 50, message = "O nome do usuário deve conter no máximo 50 caracteres")
	private String username;
	
	@Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
	private String password;
	
	public UsuarioForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User toUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setEnabled(true);
		user.addAuthority(new Authority("ROLE_USER"));
		return user;
	}

}
