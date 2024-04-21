package br.com.tarefas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Descrição da tarefa é obrigatória!")
	@Column(nullable = false, length = 150)
	private String descricao;
	
	@NotNull(message = "Status da tarefa é obrigatório!")
	@Enumerated(EnumType.STRING)
	private TarefaStatus tarefaStatus = TarefaStatus.ABERTO;
	
	private LocalDate dataEntrega;
	
	private boolean visivel;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull(message = "Categoria da tarefa é obrigatória!")
	private TarefaCategoria categoria;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull(message = "Usuário da tarefa é obrigatório!")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TarefaStatus getTarefaStatus() {
		return tarefaStatus;
	}

	public void setTarefaStatus(TarefaStatus tarefaStatus) {
		this.tarefaStatus = tarefaStatus;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public TarefaCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TarefaCategoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
