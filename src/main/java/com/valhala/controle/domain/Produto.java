package com.valhala.controle.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {

	private static final long serialVersionUID = 3107057926405112319L;
	
	private Long id;
	private String nome;
	private BigDecimal valor;
	private Integer quantidadeMinima;

	private Marca marca;
	private Tipo tipo;

	public Produto() {
		super();
	}

	public Produto(Long id, String nome, BigDecimal valor, Marca marca, Tipo tipo, Integer quantidadeMinima) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.marca = marca;
		this.tipo = tipo;
		this.quantidadeMinima = quantidadeMinima;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}
	
	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

}
