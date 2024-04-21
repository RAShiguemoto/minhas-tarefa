package br.com.tarefas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	public List<Tarefa> getTodasTarefas() {
		return tarefaRepository.findAll();
	} 
	
	public List<Tarefa> getTarefasPorDescricao(String descricao) {
		return tarefaRepository.findByDescricaoLike("%" + descricao + "%");
	}
	
	public Tarefa getTarefaPorId(Long id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Tarefa salvarTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public void deleteById(Long id) {
		tarefaRepository.deleteById(id);
	}
	
	public Tarefa iniciarTarefaPorId(Long id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		tarefa.setTarefaStatus(TarefaStatus.EM_ANDAMENTO);
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa concluirTarefaPorId(Long id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		if (TarefaStatus.CANCELADA.equals(tarefa.getTarefaStatus()))
			throw new TarefaStatusException();
		
		tarefa.setTarefaStatus(TarefaStatus.CONCLUIDA);
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa cancelarTarefaPorId(Long id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		if (TarefaStatus.CONCLUIDA.equals(tarefa.getTarefaStatus()))
			throw new TarefaStatusException("Não é possível cancelar uma tarefa concluída.");
		
		tarefa.setTarefaStatus(TarefaStatus.CANCELADA);
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
}
