package com.lluanps.condomanager_api.usuario;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario buscaOuFalha(Integer id) {
		Optional<Usuario> byId = repository.findById(id);
		return byId.orElseThrow(() -> new ObjectNotFoundException("Não existe no nosso sistema o id: ", id));
	}
	
	public Page<Usuario> buscaTodosUsuarios(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Usuario salvar(UsuarioDTO dto) {
		if(repository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email já cadastrado, tente um diferente");
		}
		
		Usuario usuario = toEntity(dto);
		return repository.save(usuario);
	}

	public Usuario update(UsuarioDTO dto, Integer id) {
	    Usuario entity = buscaOuFalha(id);

	    if (dto.getEmail() != null) {
	        entity.setEmail(dto.getEmail());
	    }
	    if (dto.getSenha() != null) {
	        entity.setSenha(dto.getSenha());
	    }
	    if (dto.getNome() != null) {
	        entity.setNome(dto.getNome());
	    }
	    if (dto.getPerfis() != null && !dto.getPerfis().isEmpty()) {
	        entity.setPerfis(dto.getPerfis()
	        		.stream()
	                .map(s -> Perfil.valueOf(s.toUpperCase()))
	                .toList());
	    }

	    return repository.save(entity);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}
	
	public Usuario toEntity(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setNome(dto.getNome());
		usuario.setPerfis(dto.getPerfis()
				.stream()
				.map(s -> Perfil.valueOf(s.toUpperCase()))
				.toList()
				);
		
		return usuario;
	}

}
