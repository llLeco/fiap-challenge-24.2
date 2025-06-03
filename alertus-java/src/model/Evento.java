package model;

import java.util.Date;

public class Evento {
    private int id;
    private String tipo;
    private double intensidade;
    private Date data;
    private Regiao regiao;

    public Evento() {}

    public Evento(int id, String tipo, double intensidade, Date data, Regiao regiao) {
        this.id = id;
        this.tipo = tipo;
        this.intensidade = intensidade;
        this.data = data;
        this.regiao = regiao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(double intensidade) {
        this.intensidade = intensidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }
}
