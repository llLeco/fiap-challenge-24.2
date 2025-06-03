package br.com.fiap.alertus.model;

import java.util.Date;

public class Event {
    private int id;
    private String type;
    private String intensity;
    private Date datetime;
    private Region region;

    public Event() {}

    public Event(String type, String intensity, Date datetime, Region region) {
        this.type = type;
        this.intensity = intensity;
        this.datetime = datetime;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
