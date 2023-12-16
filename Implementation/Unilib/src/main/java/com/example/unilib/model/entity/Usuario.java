package com.example.unilib.model.entity;

import java.util.Objects;

public class Usuario {

    private String nome;
    private String idUniversitario;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String idUniversitario, String email, String senha) {
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

    public String getIdUniversitario() {
        return idUniversitario;
    }

    public void setIdUniversitario(String idUniversitario) {
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
