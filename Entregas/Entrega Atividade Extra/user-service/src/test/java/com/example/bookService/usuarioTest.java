package com.example.bookService;

import com.example.bookService.model.entity.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class usuarioTest {

    @Test
    public void testUsuarioBuilder() {
        Usuario usuario = new Usuario.UsuarioBuilder()
                .nome("Nome do Usuário")
                .idUniversitario(12345)
                .email("usuario@example.com")
                .senha("senha123")
                .build();

        assertEquals("Nome do Usuário", usuario.getNome());
        assertEquals(12345, usuario.getIdUniversitario());
        assertEquals("usuario@example.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
    }

}
