package com.example.unilib.controller;

import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.controlador.ControladorLivros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final ControladorLivros controladorLivros;

    public LivroController(ControladorLivros controladorLivros) {
        this.controladorLivros = controladorLivros;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return controladorLivros.listarLivros();
    }

    @PostMapping
    public void salvarLivro(@RequestBody Livro livro) {
        controladorLivros.salvarLivro(livro);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable long id) {
        boolean deleted = controladorLivros.deleteUsuario(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pesquisarPorNome/{nome}")
    public List<Livro> pesquisarLivrosPorNome(@PathVariable String nome) {
        return controladorLivros.pesquisarLivrosPorNome(nome);
    }

}