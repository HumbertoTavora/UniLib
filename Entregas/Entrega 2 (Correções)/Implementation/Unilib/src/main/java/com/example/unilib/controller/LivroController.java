package com.example.unilib.controller;

import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.fachada.Fachada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final Fachada fachada;

    public LivroController(Fachada fachada) {
        this.fachada = fachada;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return fachada.listarLivros();
    }

    @PostMapping
    public void salvarLivro(@RequestBody Livro livro) {
        fachada.salvarLivro(livro);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable long id) {
        boolean deleted = fachada.deleteLivro(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/pesquisarPorNome/{nome}")
    public List<Livro> pesquisarLivrosPorNome(@PathVariable String nome) {
        return fachada.pesquisarLivrosPorNome(nome);
    }

}