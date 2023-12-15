package com.example.unilib.controller;

import com.example.unilib.model.Usuario;
import com.example.unilib.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CadastroController {

    private final UsuarioRepository usuarioRepository;

    public CadastroController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/cadastro")
    public String exibirPaginaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrarUsuario(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("cadastro");

        if (usuario.getUsername() != null && usuario.getPassword() != null) {
            if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
                modelAndView.addObject("error", "Usuário já cadastrado");
            } else {
                usuarioRepository.save(usuario);
                modelAndView.addObject("success", "Usuário cadastrado com sucesso");
                modelAndView.setView(new RedirectView("/login"));
            }
        } else {
            modelAndView.addObject("error", "Por favor, preencha todos os campos");
        }

        return modelAndView;
    }
}
