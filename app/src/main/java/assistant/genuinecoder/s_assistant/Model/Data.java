package com.jonnyjonny.s_assistant.Model;

public class Data {
    private String title;
    private String description;
    private String date;
    private String id;
    private String noOfStudents;
    private String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data(String title, String description, String date, String id,String noOfStudents,String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.noOfStudents=noOfStudents;
        this.location=location;
    }

    public Data(){

    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
