package com.valhala.controle.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class ConnectionFactory {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("properties/database");

	public static final Connection getConnection() {
		try {
			Class.forName(BUNDLE.getString("jdbc.driver"));
			Connection connection = DriverManager.getConnection(BUNDLE.getString("jdbc.url"),
					BUNDLE.getString("jdbc.user"), BUNDLE.getString("jdbc.password"));
			connection.setAutoCommit(false);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Ocorreu erro ao obter conexão com o banco de dados.", e);
		}
	}

}
