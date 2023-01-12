package br.com.alura.hotel.dao;

import java.util.List;

public interface DAO<T> {

	List<T> listar();

	void salvar(T t);

	List<T> pesquisar(String pesquisa);

	void deletar(Integer id);

	void alterar(T t);
}
