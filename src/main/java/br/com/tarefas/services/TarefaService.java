package br.com.tarefas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.model.Tarefa;
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
	
	public Tarefa getTarefasPorId(Long id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Tarefa salvarTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public void deleteById(Long id) {
		tarefaRepository.deleteById(id);
	}
}
