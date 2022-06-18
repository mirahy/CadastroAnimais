package br.edu.ifms.cadastroanimais.dao;

import java.util.List;

public interface IDao<T> {

    T inserir(T object);

    T alterar(T object);

    void excluir(Object object);

    List<T> listar();

    T buscarPorId(Object object);

}
