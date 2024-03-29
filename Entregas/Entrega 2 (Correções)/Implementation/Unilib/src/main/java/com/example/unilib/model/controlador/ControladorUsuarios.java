package com.example.unilib.model.controlador;

import com.example.unilib.model.entity.Usuario;
import com.example.unilib.model.repository.UsuarioRepository;
import com.example.unilib.model.subsistemagoogle.iSubsistemaComunicacaoGoogle;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ControladorUsuarios {

    private final UsuarioRepository usuarioRepository;


    private iSubsistemaComunicacaoGoogle subsistemaComunicacaoGoogle;

    public ControladorUsuarios(UsuarioRepository usuarioRepository, iSubsistemaComunicacaoGoogle subsistemaComunicacaoGoogle) {
        this.subsistemaComunicacaoGoogle = subsistemaComunicacaoGoogle;
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
    public Map<String, Boolean> doGoogleSignIn(Map<String, String> payload){
        return subsistemaComunicacaoGoogle.doGoogleSignIn(payload);
    }

}

