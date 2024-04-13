package br.com.tarefas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.services.TarefaService;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	@Mock
	private TarefaRepository repositorio;
	
	@InjectMocks
	private TarefaService service;
	
	@Test
	void naoDeveConcluirTarefaCancelada() {
		
		Long idExemplo = 10L;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setTarefaStatus(TarefaStatus.CANCELADA);
		
		Mockito.when(repositorio.findById(idExemplo)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(idExemplo));
	}
	
	@Test
	void naoDeveCancelarTarefaConcluida() {
		
		Long idExemplo = 10L;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setTarefaStatus(TarefaStatus.CONCLUIDA);
		
		Mockito.when(repositorio.findById(idExemplo)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.cancelarTarefaPorId(idExemplo));
	}
}
