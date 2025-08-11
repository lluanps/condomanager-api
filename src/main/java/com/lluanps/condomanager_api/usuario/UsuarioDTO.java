package com.lluanps.condomanager_api.usuario;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UsuarioDTO {

	private Integer id;

	private String nome;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	private List<String> perfis;
	
}
