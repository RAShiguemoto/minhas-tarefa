package br.com.tarefas.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.services.TarefaService;

@RestController
public class TarefaController {

	@Autowired
	private TarefaService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/tarefas")
	public List<TarefaResponse> listarTarefas() {
		
		List<Tarefa> tarefas = service.getTodasTarefas();
		
		List<TarefaResponse> tarefasResponse = tarefas
		.stream()
		.map(tarefa -> modelMapper.map(tarefa, TarefaResponse.class))
		.collect(Collectors.toList());
		
		return tarefasResponse;
	}
	
	@GetMapping("/tarefas/{id}")
	public TarefaResponse recuperarTarefa(@PathVariable Long id) {
		Tarefa tarefa = service.getTarefaPorId(id);
		
		TarefaResponse tarefaResponse = modelMapper.map(tarefa, TarefaResponse.class);
		
		return tarefaResponse;
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
