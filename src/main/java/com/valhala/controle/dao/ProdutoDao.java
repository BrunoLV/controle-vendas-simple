package com.valhala.controle.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import com.valhala.controle.domain.Marca;
import com.valhala.controle.domain.Produto;
import com.valhala.controle.domain.QMarca;
import com.valhala.controle.domain.QProduto;
import com.valhala.controle.domain.QTipo;
import com.valhala.controle.domain.Tipo;

public class ProdutoDao {

	private Connection connection;

	private QProduto qProduto = QProduto.produto;
	private QMarca qMarca = QMarca.marca;
	private QTipo qTipo = QTipo.tipo;

	private SQLTemplates template = new MySQLTemplates();

	public ProdutoDao() {
		super();
	}

	public ProdutoDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public Long save(Produto produto) {
		return new SQLInsertClause(connection, template, qProduto)
				.columns(qProduto.nome, qProduto.valor, qProduto.idMarca)
				.values(produto.getNome(), produto.getValor(), produto.getMarca().getId()).executeWithKey(Long.class);
	}

	public Long update(Produto produto) {
		return new SQLUpdateClause(connection, template, qProduto)
				.set(Arrays.asList(qProduto.nome, qProduto.valor, qProduto.idMarca),
						Arrays.asList(produto.getNome(), produto.getValor(), produto.getMarca().getId()))
				.execute();
	}

	public Long delete(Long id) {
		return new SQLDeleteClause(connection, template, qProduto).where(qProduto.id.eq(id)).execute();
	}

	public Produto findById(Long id) {
		Tuple tuple = new SQLQuery<>(connection, template)
				.select(qProduto.id, qProduto.nome, qProduto.valor, qMarca.nome, qTipo.nome, qProduto.quantidadeMinima).from(qProduto)
				.innerJoin(qMarca).on(qProduto.idMarca.eq(qMarca.id)).innerJoin(qTipo).on(qProduto.idTipo.eq(qTipo.id))
				.where(qProduto.id.eq(id)).fetchFirst();
		Marca marca = new Marca(tuple.get(qProduto.idMarca), tuple.get(qMarca.nome));
		Tipo tipo = new Tipo(tuple.get(qProduto.idTipo), tuple.get(qTipo.nome));
		Produto produto = new Produto(tuple.get(qProduto.id), tuple.get(qProduto.nome), tuple.get(qProduto.valor),
				marca, tipo, tuple.get(qProduto.quantidadeMinima));
		return produto;
	}

	public List<Produto> findAll() {
		List<Produto> produtos = new ArrayList<>();
		List<Tuple> resultados = new SQLQuery<>(connection, template)
				.select(qProduto.id, qProduto.nome, qProduto.valor, qMarca.nome, qTipo.nome, qProduto.quantidadeMinima).from(qProduto)
				.innerJoin(qMarca).on(qProduto.idMarca.eq(qMarca.id)).innerJoin(qTipo).on(qProduto.idTipo.eq(qTipo.id))
				.where(qMarca.id.eq(qProduto.idMarca)).fetch();
		for (Iterator<Tuple> iterator = resultados.iterator(); iterator.hasNext();) {
			Tuple tuple = iterator.next();
			Marca marca = new Marca(tuple.get(qProduto.idMarca), tuple.get(qMarca.nome));
			Tipo tipo = new Tipo(tuple.get(qProduto.idTipo), tuple.get(qTipo.nome));
			Produto produto = new Produto(tuple.get(qProduto.id), tuple.get(qProduto.nome), tuple.get(qProduto.valor),
					marca, tipo, tuple.get(qProduto.quantidadeMinima));
			produtos.add(produto);
		}
		return produtos;
	}

}
