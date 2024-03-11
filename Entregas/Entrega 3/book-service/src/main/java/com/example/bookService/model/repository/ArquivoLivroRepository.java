package com.example.bookService.model.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.example.bookService.model.entity.Livro;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Primary
public class ArquivoLivroRepository implements LivroRepository {
    private static final String FILE_PATH = "/home/humberto/Documents/s/book-service/src/main/resources/RepositorioArquivos/livros.json";

    private final ObjectMapper objectMapper;

    public ArquivoLivroRepository() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Livro> listarLivros() {
        try {
            List<Livro> livros = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Livro>>() {});

            Map<String, Livro> livroMap = livros.stream()
                    .collect(Collectors.toMap(Livro::getISBN, Function.identity(), (existing, replacement) -> existing));

            List<Livro> livrosSemDuplicatas = new ArrayList<>(livroMap.values());

            return livrosSemDuplicatas;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    @Override
    public void salvarLivro(Livro livro) {
        List<Livro> livros = listarLivros();

        Optional<Livro> livroExistente = livros.stream()
                .filter(l -> l.getISBN().equals(livro.getISBN()))
                .findFirst();

        if (livroExistente.isPresent()) {
            Livro livroExistenteAtualizado = livroExistente.get();
            livroExistenteAtualizado.setTitulo(livro.getTitulo());
            livroExistenteAtualizado.setAutor(livro.getAutor());
            livroExistenteAtualizado.setPalavraChave(livro.getPalavraChave());
            livroExistenteAtualizado.setQtdDisponivel(livro.getQtdDisponivel());
        } else {
            long maxId = livros.stream()
                    .mapToLong(Livro::getId)
                    .max()
                    .orElse(0L);

            livro.setId(maxId + 1);
            livros.add(livro);
        }

        try {
            objectMapper.writeValue(new File(FILE_PATH), livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Livro> pesquisarPorNome(String nome) {
        try {
            List<Livro> livros = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Livro>>() {});

            Map<String, Livro> livroMap = livros.stream()
                    .filter(livro -> livro.getTitulo().contains(nome))
                    .collect(Collectors.toMap(Livro::getISBN, Function.identity(), (existing, replacement) -> existing));

            List<Livro> livrosFiltrados = new ArrayList<>(livroMap.values());

            return livrosFiltrados;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    @Override
    public void delete(long id) {
        try {
            List<Livro> livros = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Livro>>() {});

            livros.removeIf(livro -> livro.getId().equals(id));

            objectMapper.writeValue(new File(FILE_PATH), livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
