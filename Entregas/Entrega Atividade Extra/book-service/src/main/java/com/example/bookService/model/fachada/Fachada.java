package com.example.bookService.model.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.bookService.model.controlador.ControladorLivros;
import com.example.bookService.model.entity.Livro;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class Fachada {

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


}
