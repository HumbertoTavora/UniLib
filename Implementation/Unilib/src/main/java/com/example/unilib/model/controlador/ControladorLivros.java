package com.example.unilib.model.controlador;

import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.repository.ArquivoLivroRepository;
import com.example.unilib.model.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControladorLivros {
    private final LivroRepository livroRepository;

    public ControladorLivros(ArquivoLivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {
        return livroRepository.listarLivros();
    }

    public void salvarLivro(Livro livro) {
        livroRepository.salvarLivro(livro);
    }

    public List<Livro> pesquisarLivrosPorNome(String titulo) {
        List<Livro> livros = livroRepository.pesquisarPorNome(titulo);
        return livros;
    }

}