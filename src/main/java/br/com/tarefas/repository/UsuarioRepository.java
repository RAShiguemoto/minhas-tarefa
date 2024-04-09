package br.com.tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findByNomeLikeOrderByNomeAsc(String nome);
}
