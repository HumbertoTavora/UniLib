package com.example.unilib;

import com.example.unilib.model.entity.Livro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class livroTest {

    @Test
    public void testEstaDisponivelQuandoQtdDisponivelMaiorQueZero() {
        Livro livro = new Livro.LivroBuilder("Título", "Autor", "1234567890")
                .qtdDisponivel(5)
                .build();

        assertTrue(livro.estaDisponivel(), "O livro deve estar disponível");
    }

    @Test
    public void testEstaDisponivelQuandoQtdDisponivelIgualAZero() {
        Livro livro = new Livro.LivroBuilder("Título", "Autor", "1234567890")
                .qtdDisponivel(0)
                .build();

        assertFalse(livro.estaDisponivel(), "O livro não deve estar disponível");
    }

    @Test
    public void testEstaDisponivelQuandoQtdDisponivelMenorQueZero() {
        Livro livro = new Livro.LivroBuilder("Título", "Autor", "1234567890")
                .qtdDisponivel(-2)
                .build();

        assertFalse(livro.estaDisponivel(), "O livro não deve estar disponível");
    }
}
