package br.com.alura.hotel.modelo;

import java.sql.PreparedStatement;

public interface Autenticavel {

	String getUser();

	String getPassword();

	boolean autentica(PreparedStatement pstm);
}
