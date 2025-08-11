package com.lluanps.condomanager_api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Usuario salvar(@RequestBody UsuarioDTO dto) {
		return service.salvar(dto);
	}
	
	@PutMapping("/{id}")
	public Usuario update(@RequestBody UsuarioDTO dto, @PathVariable Integer id) {
		return service.update(dto, id);
	}
	
	@GetMapping("/{id}")
	public Usuario buscaUsuarioById(@PathVariable Integer id) {
		return service.buscaOuFalha(id);
	}
	
	@GetMapping
	public Page<Usuario> buscarTodos(Pageable pageable) {
		return service.buscaTodosUsuarios(pageable);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}
	
	
	
	
	
	
	
	
}
