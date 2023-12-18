package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.entity.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class BDRUsuarioRepository implements UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> listarUsuarios() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
    @Override
    public Optional<Usuario> findByEmail(String email) {
        Usuario usuario = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(usuario);
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        entityManager.persist(usuario);
    }
    @Override
    @Transactional
    public void delete(String email) {
        entityManager.createQuery("DELETE FROM Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateEmail(String oldEmail, String newEmail) {
        entityManager.createQuery("UPDATE Usuario u SET u.email = :newEmail WHERE u.email = :oldEmail")
                .setParameter("newEmail", newEmail)
                .setParameter("oldEmail", oldEmail)
                .executeUpdate();
    }
}
