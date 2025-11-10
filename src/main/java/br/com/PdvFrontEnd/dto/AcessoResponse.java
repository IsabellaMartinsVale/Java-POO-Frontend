package br.com.PdvFrontEnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcessoResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("nomeCompleto")
    private String nomeCompleto;

    @JsonProperty("role")
    private String role;

    @JsonProperty("pessoaId")
    private Long pessoaId;

    public AcessoResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}

