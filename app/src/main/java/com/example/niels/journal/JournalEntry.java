package com.example.niels.journal;

import java.io.Serializable;
import java.sql.Timestamp;

public class JournalEntry implements Serializable {
    int id;
    String title;
    String content;
    String mood;
    Timestamp timestamp;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
