package com.example.unilib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.List;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String titulo;

    private String autor;

    private String palavraChave;

    @JsonProperty("ISBN")
    private String ISBN;

    private int qtdDisponivel;

    public Livro() {
    }

    public Livro(String titulo, String autor, String palavraChave, String ISBN, int qtdDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.palavraChave = palavraChave;
        this.ISBN = ISBN;
        this.qtdDisponivel = qtdDisponivel;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public boolean estaDisponivel() {
        return qtdDisponivel > 0;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor  + '\''  +
                ", palavraChave=" + palavraChave + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", qtdDisponivel=" + qtdDisponivel +
                '}';
    }
}
