package com.example.userService.model.repository;

import com.example.userService.model.entity.Usuario;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    List<Usuario> listarUsuarios();
    Optional<Usuario> findByEmail(String username);
    @Transactional
    void save(Usuario usuario);
    @Transactional
    void delete(String username);
    @Transactional
    void updateEmail(String oldEmail, String newEmail);
}
