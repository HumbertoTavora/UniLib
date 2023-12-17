package com.example.unilib.model.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

import java.util.Objects;
@Entity
public class Usuario {

    private String nome;
    @Id
    private long idUniversitario;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, long idUniversitario, String email, String senha) {
        this.nome = nome;
        this.idUniversitario = idUniversitario;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdUniversitario() {
        return idUniversitario;
    }

    public void setIdUniversitario(long idUniversitario) {
        this.idUniversitario = idUniversitario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean equalsUsario(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUniversitario, usuario.idUniversitario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUniversitario);
    }
}
