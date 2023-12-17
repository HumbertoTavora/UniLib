package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Usuario;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String username);
    @Transactional
    void save(Usuario usuario);
    @Transactional
    void delete(String username);
    @Transactional
    void updateEmail(String oldEmail, String newEmail);
}
