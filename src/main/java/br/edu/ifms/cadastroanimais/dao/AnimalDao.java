package br.edu.ifms.cadastroanimais.dao;

import br.edu.ifms.cadastroanimais.model.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

public class AnimalDao implements IAnimalDao {
    
private static final String JPQL = "SELECT c FROM Animal c";

    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public List<Animal> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        String condicao = "";
        List<Animal> animais = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty();
        if (hasNome) {
            condicao = " WHERE c.nome LIKE ?1 ";
        }
        Query query = em.createQuery(JPQL + condicao);
        if (hasNome) {
            animais = query.setParameter(1, "%" + nome + "%")
                    .getResultList();
        } else {
            animais = query.getResultList();
        }
        em.close();
        return animais;
    }

    @Override
    public Animal inserir(Animal object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public Animal alterar(Animal object) {
        EntityManager em = getEntityManager();
        em.detach(object);
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public void excluir(Object object) {
        Long id = (Long) object;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Animal.class, id));
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    public List<Animal> listar() {
        return buscarPorNome(null);
    }

    public Animal buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Animal cliente = em.find(Animal.class, id);
        em.close();
        return cliente;
    }

}
