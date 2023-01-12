package br.com.alura.hotel.controller;

import java.sql.Connection;

import br.com.alura.hotel.dao.SistemaInternoDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Autenticavel;

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
