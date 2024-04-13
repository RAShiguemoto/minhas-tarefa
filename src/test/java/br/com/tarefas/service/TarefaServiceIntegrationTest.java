package br.com.tarefas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.services.TarefaService;

@SpringBootTest
public class TarefaServiceIntegrationTest {

	@Autowired
	private TarefaService tarefaService;
	
	@Test
	void deveIniciarTarefa() {
		Tarefa tarefa = tarefaService.iniciarTarefaPorId(1L);
		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getTarefaStatus());
	}
}
