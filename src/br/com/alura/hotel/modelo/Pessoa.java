package br.com.alura.hotel.modelo;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {

	protected String nome;
	protected String sobrenome;
	protected String dataNascimento;
	protected String nacionalidade;
	protected String telefone;

	public Pessoa() {
	}

	public Pessoa(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public int getIdade() {
		return Period.between(LocalDate.parse(this.dataNascimento), LocalDate.now()).getYears();
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public String getTelefone() {
		return this.telefone;
	}

	@Override
	public String toString() {
		return this.nome + " " + this.sobrenome + ", " + this.dataNascimento + ", " + this.getIdade()
				+ " anos de idade, " + this.nacionalidade;
	}
}
