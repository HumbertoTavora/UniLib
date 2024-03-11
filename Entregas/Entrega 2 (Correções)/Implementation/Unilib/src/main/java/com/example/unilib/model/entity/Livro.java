package com.example.unilib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Livro implements Subject{
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

    @Transient
    private List<Observer> observers = new ArrayList<>();

    public Livro() {
    }

    public Livro(String titulo, String autor, String palavraChave, String ISBN, int qtdDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.palavraChave = palavraChave;
        this.ISBN = ISBN;
        this.qtdDisponivel = qtdDisponivel;
    }
    private Livro(LivroBuilder builder) {
        this.id = builder.id;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.palavraChave = builder.palavraChave;
        this.ISBN = builder.ISBN;
        this.qtdDisponivel = builder.qtdDisponivel;
    }

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

    public boolean estaDisponivel() {
        return qtdDisponivel > 0;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
        notifyObservers();
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
    public static class LivroBuilder {
        private final String titulo;
        private final String autor;
        private final String ISBN;
        private int qtdDisponivel;
        private Long id;
        private String palavraChave;

        public LivroBuilder(String titulo, String autor, String ISBN) {
            this.titulo = titulo;
            this.autor = autor;
            this.ISBN = ISBN;
        }

        public LivroBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LivroBuilder palavraChave(String palavraChave) {
            this.palavraChave = palavraChave;
            return this;
        }

        public LivroBuilder qtdDisponivel(int qtdDisponivel) {
            this.qtdDisponivel = qtdDisponivel;
            return this;
        }

        public Livro build() {
            return new Livro(this);
        }
    }
}
