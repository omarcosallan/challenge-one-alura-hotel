package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hotel.modelo.Hospede;

public class HospedeDAO implements DAO<Hospede> {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Hospede> listar() {
		List<Hospede> hospedes = new ArrayList<>();
		String sql = "SELECT * FROM hospedes";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
			preparedStatement.execute();
			return converteResult(hospedes, preparedStatement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void salvar(Hospede hospede) {
		String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, hospede.getNome());
			preparedStatement.setString(2, hospede.getSobrenome());
			preparedStatement.setString(3, hospede.getDataNascimento());
			preparedStatement.setString(4, hospede.getNacionalidade());
			preparedStatement.setString(5, hospede.getTelefone());
			preparedStatement.setInt(6, hospede.getIdReserva());
			preparedStatement.execute();
			try (ResultSet result = preparedStatement.getGeneratedKeys()) {
				while (result.next()) {
					hospede.setId(result.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Hospede> pesquisar(String pesquisa) {
		try {
			String sql = "SELECT * FROM hospedes WHERE id_reserva = ? OR sobrenome = ?";
			List<Hospede> hospedes = new ArrayList<>();
			try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
				pstm.setString(1, pesquisa);
				pstm.setString(2, pesquisa);
				pstm.execute();
				converteResult(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM hospedes WHERE id = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Hospede hospede) {
		try {
			String sql = "UPDATE hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setString(1, hospede.getNome());
				preparedStatement.setString(2, hospede.getSobrenome());
				preparedStatement.setString(3, hospede.getDataNascimento());
				preparedStatement.setString(4, hospede.getNacionalidade());
				preparedStatement.setString(5, hospede.getTelefone());
				preparedStatement.setInt(6, hospede.getId());
				preparedStatement.execute();
				System.out.println("editado");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> converteResult(List<Hospede> hospedes, PreparedStatement preparedStatement) {
		try (ResultSet result = preparedStatement.getResultSet()) {
			while (result.next()) {
				Hospede hospede = new Hospede(result.getInt(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getInt(7));
				hospedes.add(hospede);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
