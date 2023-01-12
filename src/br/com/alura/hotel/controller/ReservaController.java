package br.com.alura.hotel.controller;

import java.sql.Connection;

import br.com.alura.hotel.dao.ReservaDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Reserva;

public class ReservaController extends RepositoryController<Reserva> {
	
	public ReservaController() {
		Connection connection = new ConnectionFactory().connection();
		super.dao = new ReservaDAO(connection);
	}
}