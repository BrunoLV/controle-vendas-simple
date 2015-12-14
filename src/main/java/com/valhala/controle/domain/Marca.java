package com.valhala.controle.domain;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Marca implements Serializable {

	private static final long serialVersionUID = -5424708732840666250L;
	
	@SerializedName("idMarca")
	private Long id;
	private String nome;

	public Marca() {
		super();
	}
	
	public Marca(Long id) {
		super();
		this.id = id;
	}

	public Marca(Long id, String nome) {
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
