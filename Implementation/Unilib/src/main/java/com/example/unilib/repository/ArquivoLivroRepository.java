package com.example.unilib.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.unilib.model.Livro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArquivoLivroRepository {
    private static final String FILE_PATH = "src/main/resources/livros.json";

    private final ObjectMapper objectMapper;

    public ArquivoLivroRepository() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Livro> listarLivros() {
        try {
            return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Livro>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void salvarLivro(Livro livro) {
        List<Livro> livros = listarLivros();

        long maxId = livros.stream()
                .mapToLong(Livro::getId)
                .max()
                .orElse(0L);

        livro.setId(maxId + 1);

        livros.add(livro);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> pesquisarPorNome(String nome) {
        try {
            List<Livro> livros = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Livro>>() {});

            return livros.stream()
                    .filter(livro -> livro.getTitulo().contains(nome))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
