package com.valhala.controle.dao;

import java.sql.Connection;
import java.util.ArrayList;
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
import com.valhala.controle.domain.QMarca;

public class MarcaDao {

	private Connection connection;

	private QMarca qMarca = QMarca.marca;
	private SQLTemplates template = new MySQLTemplates();

	public MarcaDao() {
		super();
	}

	public MarcaDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public Long save(Marca marca) {
		return new SQLInsertClause(connection, template, qMarca).columns(qMarca.nome).values(marca.getNome()).executeWithKey(Long.class);
	}

	public Long update(Marca marca) {
		return new SQLUpdateClause(connection, template, qMarca).set(qMarca.nome, marca.getNome()).where(qMarca.id.eq(marca.getId())).execute();
	}

	public Long delete(Long id) {
		return new SQLDeleteClause(connection, template, qMarca).where(qMarca.id.eq(id)).execute();
	}

	public Marca findById(Long id) {
		Tuple tuple = new SQLQuery<>(connection, template).select(qMarca.id, qMarca.nome).from(qMarca).where(qMarca.id.eq(id)).fetchFirst();
		Marca marca = new Marca(tuple.get(qMarca.id), tuple.get(qMarca.nome));
		return marca;
	}

	public List<Marca> findAll() {
		List<Marca> marcas = new ArrayList<>();
		List<Tuple> resultados = new SQLQuery<>(connection, template).select(qMarca.id, qMarca.nome).from(qMarca).fetch();
		for (Iterator<Tuple> iterator = resultados.iterator(); iterator.hasNext();) {
			Tuple tuple = iterator.next();
			Marca marca = new Marca(tuple.get(qMarca.id), tuple.get(qMarca.nome));
			marcas.add(marca);
		}
		return marcas;
	}

}
