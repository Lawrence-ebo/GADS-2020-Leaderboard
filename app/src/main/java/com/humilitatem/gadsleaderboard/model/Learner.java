package com.humilitatem.gadsleaderboard.model;

public class Learner {
//    private int id;
    private String name;
    private String hours;
    private String score;
    private String country;
    private String badgeUrl;

    public Learner() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public String getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void setBadgeUrl(String owner) {
        this.badgeUrl = badgeUrl;
    }

}