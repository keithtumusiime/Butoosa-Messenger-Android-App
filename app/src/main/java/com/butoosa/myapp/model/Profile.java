package com.butoosa.myapp.model;

public class Profile {
    private String studentID;
    private String name;
    private String image_url;

    public Profile() {
    }

    public Profile(String studentID, String name, String image_url) {
        this.studentID = studentID;
        this.name = name;
        this.image_url = image_url;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
