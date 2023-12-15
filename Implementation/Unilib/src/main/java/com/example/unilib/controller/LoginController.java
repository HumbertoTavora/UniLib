package com.example.unilib.controller;

import com.example.unilib.model.Usuario;
import com.example.unilib.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginPost(@RequestParam String username, @RequestParam String password) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        ModelAndView modelAndView = new ModelAndView("login");

        if (usuario != null && usuario.getPassword().equals(password)) {
            modelAndView.setView(new RedirectView("/pesquisarLivro"));
        } else {
            modelAndView.addObject("error", "Usuário ou senha inválidos");
        }

        return modelAndView;
    }
   /* @GetMapping("/login/oauth2/authorization/google")
    public String loginGoogle() {
        return "redirect:/oauth2/authorization/google";
    }*/

    @GetMapping("/pesquisarLivro")
    public String pesquisarLivro() {
        return "pesquisarLivro";
    }

}
