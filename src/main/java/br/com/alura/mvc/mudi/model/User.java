package br.com.alura.mvc.mudi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String username;
	private String password;
	private Boolean enabled;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@ElementCollection()
	@CollectionTable(
			name = "authorities", 
			joinColumns = @JoinColumn(name = "username"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"username", "authority"}, name = "ix_auth_username")
	)
	private List<Authority> authorities = new ArrayList<Authority>();

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addPedido(Pedido pedido) {
		pedido.setUser(this);
		this.pedidos.add(pedido);
	}
	
	public void addAuthority(Authority authority) {
		this.authorities.add(authority);
	}

}
