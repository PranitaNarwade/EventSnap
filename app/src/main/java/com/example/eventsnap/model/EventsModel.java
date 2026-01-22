package com.example.eventsnap.model;

public class EventsModel {

    private String title;
    private String date;
    private String notes;
    private String eventId;

    public EventsModel() {
    }

    public EventsModel(String title, String date, String notes) {
        this.title = title;
        this.date = date;
        this.notes = notes;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
