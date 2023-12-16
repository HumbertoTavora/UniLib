package com.example.unilib.controller;

import com.example.unilib.model.controlador.ControladorUsuarios;
import com.example.unilib.model.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final ControladorUsuarios controladorUsuarios;
    public UsuarioController(ControladorUsuarios controladorUsuarios) {
        this.controladorUsuarios = controladorUsuarios;
    }
    @GetMapping("/login")
    public String exibirPaginaCadastro() {
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView loginPost(String email, String password) {
        return controladorUsuarios.loginPost(email, password);
    }
    @GetMapping("/cadastro")
    public String exibirPaginaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }
    @PostMapping("/cadastro")
    public ModelAndView cadastrarUsuario(Usuario usuario) {
        return controladorUsuarios.cadastrarUsuario(usuario);
    }
    @GetMapping("/pesquisarLivro")
    public String pesquisarLivro() {
        return "pesquisarLivro";
    }
}
