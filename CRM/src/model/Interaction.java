package model;

import java.util.Date;

public class Interaction {
    private int id;
    private int clientId;
    private String note;
    private Date date;

    public Interaction(int id, int clientId, String note, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.note = note;
        this.date = date;
    }

    public Interaction(int clientId, String note, Date date) {
        this(0, clientId, note, date);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
