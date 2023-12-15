package com.example.unilib.service;

import com.example.unilib.model.Livro;
import com.example.unilib.repository.ArquivoLivroRepository;

import java.util.List;

public class LivroService {
    private final ArquivoLivroRepository livroRepository;

    public LivroService(ArquivoLivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {
        return livroRepository.listarLivros();
    }

    public void salvarLivro(Livro livro) {
        livroRepository.salvarLivro(livro);
    }

    public List<Livro> pesquisarLivrosPorNome(String titulo) {
        System.out.println("Pesquisando livros por nome: " + titulo);
        List<Livro> livros = livroRepository.pesquisarPorNome(titulo);
        System.out.println("Livros encontrados: " + livros);
        return livros;
    }

}