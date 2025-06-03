package model;

import java.util.Date;

public class Alerta {
    private int id;
    private Evento evento;
    private String mensagem;
    private String nivel;
    private Date data;

    public Alerta() {}

    public Alerta(int id, Evento evento, String mensagem, String nivel, Date data) {
        this.id = id;
        this.evento = evento;
        this.mensagem = mensagem;
        this.nivel = nivel;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
