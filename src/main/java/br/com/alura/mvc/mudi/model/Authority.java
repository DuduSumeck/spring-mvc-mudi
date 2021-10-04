package br.com.alura.mvc.mudi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Authority {
	
	public Authority() {
		
	}
	
	public Authority(String authority) {
		this.authority = authority;
	}
	
	@Column(name = "authority", nullable = false)
	private String authority;
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
