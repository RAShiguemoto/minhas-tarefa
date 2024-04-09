package br.com.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios/{nome}")
	public List<Usuario> buscarPorNome(@PathVariable String nome) {
		return usuarioRepository.findByNomeLikeOrderByNomeAsc("%" + nome + "%");
	}

	@PostMapping("/usuarios")
	public Usuario salvarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
