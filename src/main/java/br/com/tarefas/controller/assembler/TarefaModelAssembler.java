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
import br.com.tarefas.model.TarefaStatus;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		TarefaResponse tarefaResponse = modelMapper.map(tarefa, TarefaResponse.class);

		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).recuperarTarefa(tarefaResponse.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas()).withRel("tarefas"));
		
		if (TarefaStatus.EM_ANDAMENTO.equals(tarefa.getTarefaStatus())) {
			tarefaModel.add(
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("concluir"),
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar"));
		}
		
		if (TarefaStatus.ABERTO.equals(tarefa.getTarefaStatus())) {
			tarefaModel.add(
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar"));
		}
		
		return tarefaModel;
	}

}
