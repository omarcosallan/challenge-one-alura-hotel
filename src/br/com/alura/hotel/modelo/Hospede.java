package br.com.alura.hotel.modelo;

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

	@Override
	public String toString() {
		return "Hóspede: " + super.toString();
	}
}
