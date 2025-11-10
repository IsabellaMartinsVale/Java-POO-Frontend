package br.com.PdvFrontEnd.model;

public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String tipo;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String cpf, String dataNascimento, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
    }

    // Construtor sem o ID (usado no front)
    public Pessoa(String nome, String cpf, String dataNascimento, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {return cpf; }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}