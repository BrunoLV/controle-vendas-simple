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
import com.valhala.controle.domain.Tipo;
import com.valhala.controle.domain.QTipo;

public class TipoDao {

	private Connection connection;

	private QTipo qTipo = QTipo.tipo;
	private SQLTemplates template = new MySQLTemplates();

	public TipoDao() {
		super();
	}

	public TipoDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public Long save(Tipo tipo) {
		return new SQLInsertClause(connection, template, qTipo).columns(qTipo.nome).values(tipo.getNome())
				.executeWithKey(Long.class);
	}

	public Long update(Tipo tipo) {
		return new SQLUpdateClause(connection, template, qTipo).set(qTipo.nome, tipo.getNome())
				.where(qTipo.id.eq(tipo.getId())).execute();
	}

	public Long delete(Long id) {
		return new SQLDeleteClause(connection, template, qTipo).where(qTipo.id.eq(id)).execute();
	}

	public Tipo findById(Long id) {
		Tuple tuple = new SQLQuery<>(connection, template).select(qTipo.id, qTipo.nome).from(qTipo)
				.where(qTipo.id.eq(id)).fetchFirst();
		Tipo tipo = new Tipo(tuple.get(0, Long.class), tuple.get(1, String.class));
		return tipo;
	}

	public List<Tipo> findAll() {
		List<Tipo> tipos = new ArrayList<>();
		List<Tuple> resultados = new SQLQuery<>(connection, template).select(qTipo.id, qTipo.nome).from(qTipo).fetch();
		for (Iterator<Tuple> iterator = resultados.iterator(); iterator.hasNext();) {
			Tuple tuple = iterator.next();
			Tipo tipo = new Tipo(tuple.get(0, Long.class), tuple.get(1, String.class));
			tipos.add(tipo);
		}
		return tipos;
	}

}
