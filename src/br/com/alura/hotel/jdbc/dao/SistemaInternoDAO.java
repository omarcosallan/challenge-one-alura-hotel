package br.com.alura.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.alura.hotel.jdbc.modelo.Autenticavel;

public class SistemaInternoDAO {

	private Connection connection;

	public SistemaInternoDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean login(Autenticavel autenticavel) {
		String sql = "SELECT * FROM USUARIO WHERE USUARIO = ? AND PASSWORD = ?";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
			preparedStatement.setString(1, autenticavel.getUser());
			preparedStatement.setString(2, autenticavel.getPassword());
			preparedStatement.execute();
			return autenticavel.autentica(preparedStatement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
