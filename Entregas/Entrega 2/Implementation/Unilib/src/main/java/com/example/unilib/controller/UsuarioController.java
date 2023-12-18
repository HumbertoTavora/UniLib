package com.example.unilib.controller;

import com.example.unilib.model.fachada.Fachada;
import com.example.unilib.model.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final Fachada fachada;
    public UsuarioController(Fachada fachada) {
        this.fachada = fachada;
    }
    @GetMapping("/login")
    public String exibirPaginaCadastro() {
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView loginPost(String email, String password) {
        return fachada.loginPost(email, password);
    }
    @GetMapping("/cadastro")
    public String exibirPaginaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }
    @PostMapping("/cadastro")
    public ModelAndView cadastrarUsuario(Usuario usuario) {
        return fachada.cadastrarUsuario(usuario);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String email) {
        boolean deleted = fachada.deleteUsuario(email);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{oldEmail}/{newEmail}")
    public ResponseEntity<Void> updateEmail(@PathVariable String oldEmail, @PathVariable String newEmail) {
        boolean updated = fachada.updateEmail(oldEmail, newEmail);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/pesquisarLivro")
    public String pesquisarLivro() {
        return "pesquisarLivro";
    }

}
