package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Livro;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class BDRLivroRepository implements LivroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Livro> listarLivros() {
        return entityManager.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
    }

    @Override
    @Transactional
    public void salvarLivro(Livro livro) {
        entityManager.persist(livro);
    }

    @Override
    public List<Livro> pesquisarPorNome(String nome) {
        return entityManager.createQuery("SELECT l FROM Livro l WHERE l.titulo LIKE :nome", Livro.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
    @Override
    @Transactional
    public void delete(long id) {
        entityManager.createQuery("DELETE FROM Livro l WHERE l.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }


}
