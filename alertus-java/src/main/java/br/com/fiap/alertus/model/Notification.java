package br.com.fiap.alertus.model;

import java.util.Date;

public class Notification {
    private int id;
    private String message;
    private String level;
    private Date datetime;
    private Event event;

    // Constructors
    public Notification() {}

    public Notification(String message, String level, Date datetime, Event event) {
        this.message = message;
        this.level = level;
        this.datetime = datetime;
        this.event = event;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
