package com.springfield.y.writeley.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

@Entity(tableName = "notes")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String body;

    @ColumnInfo(name = "updated_at")
    private Date udpatedAt;

    @Ignore
    public Note(String title, String body, Date udpatedAt) {
        this.title = title;
        this.body = body;
        this.udpatedAt = udpatedAt;
    }

    public Note(int id, String title, String body, Date udpatedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.udpatedAt = udpatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getUdpatedAt() {
        return udpatedAt;
    }

    public void setUdpatedAt(Date udpatedAt) {
        this.udpatedAt = udpatedAt;
    }
}
