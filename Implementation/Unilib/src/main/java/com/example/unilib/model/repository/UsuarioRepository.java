package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String username);

    void save(Usuario usuario);
}
