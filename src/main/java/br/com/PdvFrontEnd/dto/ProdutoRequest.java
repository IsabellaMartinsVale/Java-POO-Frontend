package br.com.PdvFrontEnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoRequest {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("referencia")
    private String referencia;

    @JsonProperty("fornecedor")
    private String fornecedor;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("marca")
    private String marca;

    public ProdutoRequest() {}

    public ProdutoRequest(String nome, String referencia, String fornecedor, String categoria, String marca) {
        this.nome = nome;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.marca = marca;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}

