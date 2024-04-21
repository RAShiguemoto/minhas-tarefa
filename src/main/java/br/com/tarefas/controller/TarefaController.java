package br.com.tarefas.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.assembler.TarefaModelAssembler;
import br.com.tarefas.controller.request.TarefaResquest;
import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.services.TarefaService;

@RestController
@RequestMapping(path = "/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService service;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TarefaModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<TarefaResponse>> listarTarefas() {

		List<Tarefa> tarefas = service.getTodasTarefas();

		List<EntityModel<TarefaResponse>> tarefasModel = tarefas.stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(tarefasModel, WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<TarefaResponse> recuperarTarefa(@PathVariable Long id) {
		Tarefa tarefa = service.getTarefaPorId(id);

		return assembler.toModel(tarefa);
	}

	@PostMapping
	public ResponseEntity<EntityModel<TarefaResponse>> salvarNovaTarefa(@RequestBody TarefaResquest tarefaReq) {
		Tarefa tarefa = modelMapper.map(tarefaReq, Tarefa.class);
		Tarefa tarefaSalva = service.salvarTarefa(tarefa);

		EntityModel<TarefaResponse> tarefaModel = assembler.toModel(tarefaSalva);
		
		return ResponseEntity.created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tarefaModel);
	}

	@DeleteMapping("/{id}")
	public void deletarTarefa(@PathVariable Long tarefaId) {
		service.deleteById(tarefaId);
	}

	@PutMapping("/{id}/iniciar")
	public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Long id) {
		Tarefa tarefa = service.iniciarTarefaPorId(id);
		return assembler.toModel(tarefa);
	}

	@PutMapping("/{id}/concluir")
	public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Long id) {
		Tarefa tarefa = service.concluirTarefaPorId(id);
		return assembler.toModel(tarefa);
	}

	@PutMapping("/{id}/cancelar")
	public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Long id) {
		Tarefa tarefa = service.cancelarTarefaPorId(id);
		return assembler.toModel(tarefa);
	}
}
