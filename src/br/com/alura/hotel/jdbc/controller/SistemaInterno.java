package br.com.alura.hotel.jdbc.controller;

import java.sql.Connection;

import br.com.alura.hotel.jdbc.dao.SistemaInternoDAO;
import br.com.alura.hotel.jdbc.factory.ConnectionFactory;
import br.com.alura.hotel.jdbc.modelo.Autenticavel;

public class SistemaInterno {

	private SistemaInternoDAO sistemaInternoDAO;
	private Autenticavel autenticavel;

	public SistemaInterno(Autenticavel autenticavel) {
		Connection connection = new ConnectionFactory().connection();
		this.sistemaInternoDAO = new SistemaInternoDAO(connection);
		this.autenticavel = autenticavel;
	}

	public boolean login() {
		return this.sistemaInternoDAO.login(this.autenticavel);
	}
}
