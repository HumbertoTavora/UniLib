package com.example.unilib.repository;

import com.example.unilib.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByUsername(String username);

    void save(Usuario usuario);
}
