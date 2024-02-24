package com.example.bookService.controller;

import com.example.bookService.model.entity.Livro;
import com.example.bookService.model.fachada.Fachada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LivroController {
    private final Fachada fachada;

    public LivroController(Fachada fachada) {
        this.fachada = fachada;
    }

    @GetMapping("/list")
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