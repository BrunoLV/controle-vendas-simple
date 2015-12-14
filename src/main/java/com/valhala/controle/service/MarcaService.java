package com.valhala.controle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.valhala.controle.dao.MarcaDao;
import com.valhala.controle.domain.Marca;

public class MarcaService {

	private Connection connection;

	public MarcaService() {
		super();
	}

	public MarcaService(Connection connection) {
		super();
		this.connection = connection;
	}

	public Marca save(Marca marca) {
		try {
			MarcaDao dao = new MarcaDao(connection);
			Long id = dao.save(marca);
			connection.commit();
			return dao.findById(id);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return marca;
	}

	public void update(Marca marca) {
		try {
			MarcaDao dao = new MarcaDao(connection);
			dao.update(marca);
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
			MarcaDao dao = new MarcaDao(connection);
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

	public Marca findById(Long id) {
		try {
			MarcaDao dao = new MarcaDao(connection);
			return dao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Marca> findAll() {
		try {
			MarcaDao dao = new MarcaDao(connection);
			return dao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
