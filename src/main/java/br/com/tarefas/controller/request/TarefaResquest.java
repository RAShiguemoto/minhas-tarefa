package br.com.tarefas.controller.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TarefaResquest {

	private Long id;

	@NotBlank(message = "Descrição é obrigatória!")
	private String descricao;

	@NotNull(message = "Data Entrega é obrigatória!")
	@FutureOrPresent(message = "Data Entrega deve ser uma data futura!")
	private LocalDate dataEntrega;

	@NotNull(message = "Categoria é obrigatória!")
	private Long categoriaId;

	@NotNull(message = "Usuário é obrigatório!")
	private Long usuarioId;

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

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
