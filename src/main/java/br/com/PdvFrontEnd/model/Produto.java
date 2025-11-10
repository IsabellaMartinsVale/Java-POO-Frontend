package br.com.PdvFrontEnd.model;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private String referencia;
    private String fornecedor;
    private String categoria;
    private String marca;
    private boolean isCombustivel; // Para saber se aplicamos o imposto

    // Construtor para o formulário de cadastro
    public Produto(String nome, double preco, String referencia, String fornecedor, String categoria, String marca) {
        this.nome = nome;
        this.preco = preco;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.marca = marca;
        // Define se é combustível com base na categoria ou nome
        if (categoria != null) {
            String catLower = categoria.toLowerCase();
            this.isCombustivel = catLower.contains("combustivel") || catLower.contains("gasolina") || catLower.contains("etanol") || catLower.contains("diesel");
        }
    }

    // Construtor simplificado para o PDV
    public Produto(String nome, double preco, boolean isCombustivel) {
        this.nome = nome;
        this.preco = preco;
        this.isCombustivel = isCombustivel;
    }

    public Produto(String nome, String referencia, String fornecedor, String categoria, String marca) {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public boolean isCombustivel() { return isCombustivel; }
    public void setCombustivel(boolean combustivel) { isCombustivel = combustivel; }

    @Override
    public String toString() {
        return this.nome;
    }
}