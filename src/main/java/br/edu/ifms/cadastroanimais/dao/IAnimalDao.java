package br.edu.ifms.cadastroanimais.dao;

import br.edu.ifms.cadastroanimais.model.Animal;
import java.util.List;

/**
 *
 * @author gustavo
 */
public interface IAnimalDao extends IDao<Animal>{
    
    List<Animal> buscarPorNome(String nome);
}
