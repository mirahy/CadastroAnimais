package br.edu.ifms.cadastroanimais.dao;

import br.edu.ifms.cadastroanimais.model.Animal;
import java.util.List;

public interface IAnimalDao extends IDao<Animal>{
    
    List<Animal> buscarPorNome(String nome);
}
