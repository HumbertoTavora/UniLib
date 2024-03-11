package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.entity.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Repository
@Primary
public class ArquivoUsuarioRepository implements UsuarioRepository {

    private static final String FILE_PATH = "src/main/resources/RepositorioArquivos/usuarios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Usuario> listarUsuarios() {
        try {
            List<Usuario> usuarios = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Usuario>>() {});

            Map<String, Usuario> usuarioMap = usuarios.stream()
                    .collect(Collectors.toMap(Usuario::getEmail, Function.identity(), (existing, replacement) -> existing));

            List<Usuario> usuariosSemDuplicatas = new ArrayList<>(usuarioMap.values());

            return usuariosSemDuplicatas;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    @Override
    public Optional<Usuario> findByEmail(String username) {
        try {
            List<Usuario> usuarios = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Usuario>>() {});

            return usuarios.stream()
                    .filter(usuario -> usuario.getEmail().equals(username))
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

    @Override
    public void delete(String username) {
        try {
            List<Usuario> usuarios = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Usuario>>() {});

            usuarios.removeIf(usuario -> usuario.getEmail().equals(username));

            objectMapper.writeValue(new File(FILE_PATH), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateEmail(String oldEmail, String newEmail) {
        try {
            List<Usuario> usuarios = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Usuario>>() {});

            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(oldEmail)) {
                    usuario.setEmail(newEmail);
                    break;
                }
            }

            objectMapper.writeValue(new File(FILE_PATH), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
