package com.example.bookService.model.repository;

import com.example.bookService.model.entity.Livro;
import javax.transaction.Transactional;
import java.util.*;

public interface LivroRepository {

    List<Livro> listarLivros();
    @Transactional
    void salvarLivro(Livro livro);
    List<Livro> pesquisarPorNome(String nome);

    @Transactional
    void delete(long id);
}
