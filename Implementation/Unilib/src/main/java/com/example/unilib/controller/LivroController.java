package com.example.unilib.controller;

import com.example.unilib.model.Livro;
import com.example.unilib.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @PostMapping
    public void salvarLivro(@RequestBody Livro livro) {
        livroService.salvarLivro(livro);
    }



    @GetMapping("/pesquisarPorNome/{nome}")
    public List<Livro> pesquisarLivrosPorNome(@PathVariable String nome) {
        return livroService.pesquisarLivrosPorNome(nome);
    }

}
