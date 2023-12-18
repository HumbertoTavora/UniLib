package com.example.unilib.model.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.unilib.model.controlador.ControladorLivros;
import com.example.unilib.model.controlador.ControladorUsuarios;
import com.example.unilib.model.entity.Livro;
import com.example.unilib.model.entity.Usuario;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class Fachada {

    @Autowired
    private ControladorUsuarios controladorUsuarios;

    @Autowired
    private ControladorLivros controladorLivros;

    // Livros
    public List<Livro> listarLivros(){
        return controladorLivros.listarLivros();
    }
    public void salvarLivro(Livro livro) {
        controladorLivros.salvarLivro(livro);
    }
    public List<Livro> pesquisarLivrosPorNome(String titulo) {
        return controladorLivros.pesquisarLivrosPorNome(titulo);
    }
    public boolean deleteLivro(long id){
        return controladorLivros.deleteLivro(id);
    }

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
