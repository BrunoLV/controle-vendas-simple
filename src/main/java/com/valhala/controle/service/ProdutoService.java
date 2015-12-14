package com.valhala.controle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.valhala.controle.dao.ProdutoDao;
import com.valhala.controle.domain.Produto;

public class ProdutoService {
	
	private Connection connection;

	public ProdutoService() {
		super();
	}

	public ProdutoService(Connection connection) {
		super();
		this.connection = connection;
	}

	public Produto save(Produto produto) {
		try {
			ProdutoDao dao = new ProdutoDao(connection);
			Long id = dao.save(produto);
			connection.commit();
			return dao.findById(id);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return produto;
	}

	public void update(Produto produto) {
		try {
			ProdutoDao dao = new ProdutoDao(connection);
			dao.update(produto);
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
			ProdutoDao dao = new ProdutoDao(connection);
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

	public Produto findById(Long id) {
		try {
			ProdutoDao dao = new ProdutoDao(connection);
			return dao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> findAll() {
		try {
			ProdutoDao dao = new ProdutoDao(connection);
			return dao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
