package com.valhala.controle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.valhala.controle.dao.TipoDao;
import com.valhala.controle.domain.Tipo;

public class TipoService {
	
	private Connection connection;

	public TipoService() {
		super();
	}

	public TipoService(Connection connection) {
		super();
		this.connection = connection;
	}

	public Tipo save(Tipo tipo) {
		try {
			TipoDao dao = new TipoDao(connection);
			Long id = dao.save(tipo);
			connection.commit();
			return dao.findById(id);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return tipo;
	}

	public void update(Tipo tipo) {
		try {
			TipoDao dao = new TipoDao(connection);
			dao.update(tipo);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}

	public void delete(Long id) {
		try {
			TipoDao dao = new TipoDao(connection);
			dao.delete(id);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}

	public Tipo findById(Long id) {
		try {
			TipoDao dao = new TipoDao(connection);
			return dao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tipo> findAll() {
		try {
			TipoDao dao = new TipoDao(connection);
			return dao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
