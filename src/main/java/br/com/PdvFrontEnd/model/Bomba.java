package br.com.PdvFrontEnd.model;

public class Bomba {
    private int numeroBomba;
    private String combustivel;
    private double litros;
    private double valorTotal;
    private boolean ativa;

    public Bomba() {
        this.ativa = true;
    }

    public Bomba(int numeroBomba, String combustivel, double litros, double valorTotal) {
        this.numeroBomba = numeroBomba;
        this.combustivel = combustivel;
        this.litros = litros;
        this.valorTotal = valorTotal;
        this.ativa = true;
    }

    public int getNumeroBomba() {
        return numeroBomba;
    }

    public void setNumeroBomba(int numeroBomba) {
        this.numeroBomba = numeroBomba;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}

