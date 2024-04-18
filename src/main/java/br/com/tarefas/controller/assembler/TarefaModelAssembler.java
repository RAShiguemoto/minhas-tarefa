package br.com.tarefas.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.tarefas.controller.TarefaController;
import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		TarefaResponse tarefaResponse = modelMapper.map(tarefa, TarefaResponse.class);

		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).recuperarTarefa(tarefaResponse.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas()).withRel("Listar Tarefas"));
		
		return tarefaModel;
	}

}
