package model;

public class Regiao {
    private int id;
    private String nome;
    private double latitude;
    private double longitude;

    public Regiao() {}

    public Regiao(int id, String nome, double latitude, double longitude) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
