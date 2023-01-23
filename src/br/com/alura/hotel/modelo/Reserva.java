package br.com.alura.hotel.modelo;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Reserva {

	private Integer id;
	private String dataEntrada;
	private String dataSaida;
	private Double valor;
	private String formaDePagamento;
	private static final double DIARIA = 150.0;

	public Reserva(String dataEntrada, String dataSaida, Double valor, String formaDePagamento) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
	}

	public Reserva(Integer id, String dataEntrada, String dataSaida, Double valor, String formaDePagamento) {
		this(dataEntrada, dataSaida, valor, formaDePagamento);
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataEntrada() {
		return this.dataEntrada;
	}

	public String getDataEntrada(SimpleDateFormat sdf) {
		return sdf.format(Date.valueOf(this.dataEntrada));
	}

	public String getDataSaida() {
		return this.dataSaida;
	}

	public String getDataSaida(SimpleDateFormat sdf) {
		return sdf.format(Date.valueOf(this.dataSaida));
	}

	public Double getValor() {
		return this.valor;
	}

	public String getValor(Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(this.valor);
	}

	public String getFormaDePagamento() {
		return this.formaDePagamento;
	}

	public static double valor(String dataEntrada, String dataSaida) {
		long dias = ChronoUnit.DAYS.between(LocalDate.parse(dataEntrada), LocalDate.parse(dataSaida)) + 1;
		return dias * Reserva.DIARIA;
	}

	@Override
	public String toString() {
		return this.id + ", " + this.dataEntrada + ", " + this.valor + ", " + this.dataSaida + ", "
				+ this.formaDePagamento;
	}
}
