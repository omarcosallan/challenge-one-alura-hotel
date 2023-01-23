package br.com.alura.hotel.modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Hospede extends Pessoa {

	private Integer id;
	private Integer idReserva;

	public Hospede(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone,
			Integer idReserva) {
		super(nome, sobrenome, dataNascimento, nacionalidade, telefone);
		this.idReserva = idReserva;
	}

	public Hospede(Integer id, String nome, String sobrenome, String dataNascimento, String nacionalidade,
			String telefone, Integer idReserva) {
		this(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva);
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdReserva() {
		return this.idReserva;
	}

	public String getDataNascimento(SimpleDateFormat sdf) {
		return sdf.format(Date.valueOf(this.dataNascimento));
	}
	
	@Override
	public String toString() {
		return "Hóspede: " + super.toString();
	}
}
