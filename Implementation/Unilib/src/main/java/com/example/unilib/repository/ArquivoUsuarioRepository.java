package com.example.unilib.repository;

import com.example.unilib.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ArquivoUsuarioRepository implements UsuarioRepository {

    private static final String FILE_PATH = "src/main/resources/usuarios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Optional<Usuario> findByUsername(String username) {
        try {
            List<Usuario> usuarios = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Usuario>>() {});

            return usuarios.stream()
                    .filter(usuario -> usuario.getUsername().equals(username))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Usuario usuario) {
        try {
            List<Usuario> usuarios = new ArrayList<>();

            File file = new File(FILE_PATH);
            if (file.exists()) {
                usuarios = objectMapper.readValue(file, new TypeReference<List<Usuario>>() {});
            }

            usuarios.add(usuario);

            objectMapper.writeValue(file, usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
