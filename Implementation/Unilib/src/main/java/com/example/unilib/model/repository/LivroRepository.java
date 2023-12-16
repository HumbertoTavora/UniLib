package com.example.unilib.model.repository;

import com.example.unilib.model.entity.Livro;
import java.util.*;

public interface LivroRepository {

    List<Livro> listarLivros();

    void salvarLivro(Livro livro);

    List<Livro> pesquisarPorNome(String nome);
}
