package com.cadastro;

/**
 * Classe modelo que representa uma Pessoa cadastrada.
 * Armazena os dados: nome, CPF, e-mail e telefone.
 */
public class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome     = nome;
        this.cpf      = cpf;
        this.email    = email;
        this.telefone = telefone;
    }

    // -------------------------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // -------------------------------------------------------------------------
    // toString — facilita exibição no console
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Nome: %-30s | CPF: %-15s | E-mail: %-30s | Telefone: %s",
                nome, cpf, email, telefone);
    }
}
