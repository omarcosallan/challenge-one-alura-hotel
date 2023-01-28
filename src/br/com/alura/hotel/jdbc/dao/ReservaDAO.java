package br.com.alura.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hotel.jdbc.modelo.Reserva;

public class ReservaDAO implements DAO<Reserva> {

	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM reservas";
			try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
				pstm.execute();
				converteResult(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void salvar(Reserva reserva) {
		String sql = "INSERT INTO reservas (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?, ?, ?, ?);";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, reserva.getDataEntrada().toString());
			preparedStatement.setString(2, reserva.getDataSaida().toString());
			preparedStatement.setDouble(3, reserva.getValor());
			preparedStatement.setString(4, reserva.getFormaDePagamento());
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				while (resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Reserva> pesquisar(String pesquisa) {
		try {
			String sql = "SELECT * FROM reservas WHERE id = ?";
			List<Reserva> reservas = new ArrayList<>();
			try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
				pstm.setString(1, pesquisa);
				pstm.execute();
				converteResult(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM reservas WHERE ID = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Reserva reserva) {
		try {
			String sql = "UPDATE reservas SET DATA_ENTRADA = ?, DATA_SAIDA = ?, VALOR = ?, FORMA_PAGAMENTO = ? WHERE ID = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setString(1, reserva.getDataEntrada().toString());
				preparedStatement.setString(2, reserva.getDataSaida().toString());
				preparedStatement.setDouble(3, reserva.getValor());
				preparedStatement.setString(4, reserva.getFormaDePagamento());
				preparedStatement.setInt(5, reserva.getId());
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> converteResult(List<Reserva> reservas, PreparedStatement preparedStatement) {
		try (ResultSet result = preparedStatement.getResultSet()) {
			while (result.next()) {
				Reserva reserva = new Reserva(result.getInt(1), LocalDate.parse(result.getString(2)),
						LocalDate.parse(result.getString(3)), result.getDouble(4), result.getString(5));
				reservas.add(reserva);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
