package br.com.alura.hotel.controller;

import java.sql.Connection;

import br.com.alura.hotel.dao.HospedeDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Hospede;

public class HospedeController extends Repository<Hospede> {
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().connection();
		super.dao = new HospedeDAO(connection);
	}
}
