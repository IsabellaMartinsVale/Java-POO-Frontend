package br.com.PdvFrontEnd.model;

import java.util.Date;

public class Custo {

    // atributos
    private Long id;
    private double imposto;
    private double frete;
    private double custoVariavel;
    private double custoFixo;
    private double margemLucro;
    private Date dataProcessamento;

    // construtor com parâmetros
    public Custo(Long id, double imposto, double frete, double custoVariavel, double custoFixo, double margemLucro, Date dataProcessamento) {
        this.id = id;
        this.imposto = imposto;
        this.frete = frete;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }

    // getters
    public Long getId() {
        return id;
    }

    public double getImposto() {
        return imposto;
    }

    public double getFrete() {
        return frete;
    }

    public double getCustoVariavel() {
        return custoVariavel;
    }

    public double getCustoFixo() {
        return custoFixo;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setCustoVariavel(double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public void setCustoFixo(double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
}
