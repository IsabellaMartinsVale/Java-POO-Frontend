package br.com.PdvFrontEnd.model;
public class Acesso {
    /*atributos*/
    private Long id;
    private String usuario;
    private String senha;
    private String nomeCompleto;
    private String role;

    /*constructor*/
    public Acesso(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    /*getters*/
    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getRole() {
        return role;
    }

    /*setters*/
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setRole(String role) {
        this.role = role;
    }
}