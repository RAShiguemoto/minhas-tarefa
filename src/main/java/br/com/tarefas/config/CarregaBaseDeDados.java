package br.com.tarefas.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.TarefaCategoriaRepository;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarefaCategoriaRepository categoriaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@Bean
	CommandLineRunner executar() {
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha("123456");
			usuarioRepository.save(usuario);
			
			TarefaCategoria categoria = new TarefaCategoria();
			categoria.setNome("Estudos");
			categoriaRepository.save(categoria);
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Äprender Spring Boot");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setTarefaStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(categoria);
			tarefa.setUsuario(usuario);
			tarefaRepository.save(tarefa);
		};
	}
}
