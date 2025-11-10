package br.com.PdvFrontEnd.model;
public class Contato {
    /*atributos */
    private Long id;
    private String telefone;
    private String email;
    private String endereco;

    /*constructor*/
    public Contato(Long id, String telefone, String email, String endereco){
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    /*getters*/
    public Long getId() {
        return id;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }
    /*setters*/
    public void setId(Long id) {
        this.id = id;
    }
    public void setTelefone (String telefone){
        this.telefone = telefone;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setEndereco (String endereco){
        this.endereco = endereco;
    }
}