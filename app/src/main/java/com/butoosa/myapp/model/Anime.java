package com.butoosa.myapp.model;

public class Anime {
    private String studentID;
    private String name;
    private String email;
    private String faculty;
    private String course;
    private String contact;
    private String img_url;
    private String dept;
    private String year;
    private String hostel;

    private String nationality;
    private String district;
    private String county;
    private String subcounty;
    private String village;

    public Anime(){

    }

    public Anime(String studentID, String name, String email, String faculty, String course, String contact, String img_url, String dept, String year, String hostel, String nationality, String district, String county, String subcounty, String village) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.course = course;
        this.contact = contact;
        this.img_url = img_url;
        this.dept = dept;
        this.year = year;
        this.hostel = hostel;
        this.nationality = nationality;
        this.district = district;
        this.county = county;
        this.subcounty = subcounty;
        this.village = village;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCourse() {
        return course;
    }

    public String getContact() {
        return contact;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDept() {
        return dept;
    }

    public String getYear() {
        return year;
    }

    public String getHostel() {
        return hostel;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDistrict() {
        return district;
    }

    public String getCounty() {
        return county;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public String getVillage() {
        return village;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
