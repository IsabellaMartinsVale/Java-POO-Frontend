package br.com.PdvFrontEnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcessoRequest {
    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("pessoaId")
    private Long pessoaId;

    @JsonProperty("role")
    private String role;

    public AcessoRequest() {}

    public AcessoRequest(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public AcessoRequest(String usuario, String senha, Long pessoaId, String role) {
        this.usuario = usuario;
        this.senha = senha;
        this.pessoaId = pessoaId;
        this.role = role;
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

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

