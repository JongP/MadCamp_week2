package com.example.madcamp_week2.model;

public class Post {

    private String id;
    private String title;
    private String content;
    private double rate;

    private String writer;
    private String rest;

    // PostResult 이외의 추가 필드
    private String restName;

    public Post(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public Post(String title, String content, double rate, String writer, String rest, String restName) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.writer = writer;
        this.rest = rest;
        this.restName = restName;
    }

    public Post(String id, String title, String content, double rate, String writer, String rest, String restName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.writer = writer;
        this.rest = rest;
        this.restName = restName;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Post) {
            Post p = (Post) obj;

            return p.getId().equals(this.id);
        }
        return false;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
