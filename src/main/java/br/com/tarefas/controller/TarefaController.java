package br.com.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.services.TarefaService;

@RestController
public class TarefaController {

	@Autowired
	private TarefaService service;
	
	@GetMapping("/tarefas")
	public List<Tarefa> listarTarefas() {
		return service.getTodasTarefas();
	}
	
	@GetMapping("/tarefas/{id}")
	public Tarefa recuperarTarefa(@PathVariable Long tarefaId) {
		return service.getTarefaPorId(tarefaId);
	}
	
	@PostMapping("/tarefas")
	public Tarefa salvarNovaTarefa(@RequestBody Tarefa tarefa) {
		return service.salvarTarefa(tarefa);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void deletarTarefa(@PathVariable Long tarefaId) {
		service.deleteById(tarefaId);
	}
}
