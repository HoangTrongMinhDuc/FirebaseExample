package com.hoangtrongminhduc.html5.dev.firebaseexample;

/**
 * Created by HTML5 on 14/10/2017.
 */

public class JournalEntry {
    private String title;
    private String content;
    private String date;
    private String key;
    public JournalEntry(){

    }

    public JournalEntry(String title, String content, String date, String key) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.key = key;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}