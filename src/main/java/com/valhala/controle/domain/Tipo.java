package com.valhala.controle.domain;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Tipo implements Serializable {

	private static final long serialVersionUID = 1107230756274830208L;
	
	@SerializedName("idTipo")
	private Long id;
	private String nome;
	
	public Tipo() {
		super();
	}
	
	public Tipo(Long id) {
		super();
		this.id = id;
	}

	public Tipo(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
