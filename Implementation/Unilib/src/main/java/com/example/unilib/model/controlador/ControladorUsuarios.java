package com.example.unilib.model.controlador;

import com.example.unilib.model.entity.Usuario;
import com.example.unilib.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;
@Service
public class ControladorUsuarios {

    private final UsuarioRepository usuarioRepository;
    public ControladorUsuarios(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public ModelAndView loginPost(String email, String password) {
        ModelAndView modelAndView = new ModelAndView("login");

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && usuario.get().getSenha().equals(password)) {
            modelAndView.setView(new RedirectView("/pesquisarLivro"));
        } else {
            modelAndView.addObject("error", "Usuário ou senha inválidos");
        }

        return modelAndView;
    }
    public ModelAndView cadastrarUsuario(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("cadastro");

        if (usuario.getEmail() != null && usuario.getSenha() != null && usuario.getIdUniversitario() != 0 && usuario.getNome() != null) {
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
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
    public boolean deleteUsuario(String email){
        usuarioRepository.delete(email);

        return false;
    }

    public boolean updateEmail(String oldEmail, String newEmail) {
        usuarioRepository.updateEmail(oldEmail,newEmail);
        return false;

    }
}

