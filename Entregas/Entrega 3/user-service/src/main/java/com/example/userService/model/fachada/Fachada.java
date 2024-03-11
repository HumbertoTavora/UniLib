package com.example.userService.model.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.userService.model.controlador.ControladorUsuarios;
import com.example.userService.model.entity.Usuario;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class Fachada {

    @Autowired
    private ControladorUsuarios controladorUsuarios;

    // Usuários
    public ModelAndView loginPost(String email, String password) {
        return controladorUsuarios.loginPost(email, password);
    }
    public ModelAndView cadastrarUsuario(Usuario usuario) {
        return controladorUsuarios.cadastrarUsuario(usuario);
    }
    public boolean deleteUsuario(String email){
        return controladorUsuarios.deleteUsuario(email);
    }
    public boolean updateEmail(String oldEmail, String newEmail){
        return controladorUsuarios.updateEmail(oldEmail,newEmail);
    }

}
