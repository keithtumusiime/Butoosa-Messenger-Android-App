package com.butoosa.myapp.model;

public class Posts {
    private String post_id;
    private String post_sid;
    private String post_author;
    private String post_body;
    private String post_date;
    private String post_img_url;
    private String no_of_likes;
    private String no_of_comments;
    private String post_image;


    public Posts() {
    }

    public Posts(String post_id, String post_image, String no_of_comments, String post_sid, String post_author, String post_body, String post_date,String post_img_url, String no_of_likes) {
        this.post_id = post_id;
        this.post_sid = post_sid;
        this.post_author = post_author;
        this.post_body = post_body;
        this.post_date = post_date;
        this.post_img_url = post_img_url;
        this.no_of_comments = no_of_comments;
        this.no_of_likes = no_of_likes;
        this.post_image = post_image;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getPost_sid() {
        return post_sid;
    }

    public String getPost_author() {
        return post_author;
    }
    public String getPost_img_url() {
        return post_author;
    }

    public String getPost_body() {
        return post_body;
    }

    public String getPost_date() {
        return post_date;
    }
    public String getNo_of_likes() {
        return no_of_likes;
    }

    public String getNo_of_comments(){
        return no_of_comments;
    }
    public String getPost_image(){
        return post_image;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public void setPost_sid(String post_sid) {
        this.post_sid = post_sid;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public void setNo_of_likes(String no_of_likes){
        this.no_of_likes = no_of_likes;
    }
    public void setNo_of_comments(String no_of_comments){
        this.no_of_comments = no_of_comments;
    }
    public void setPost_img_url(String post_img_url) {
        this.post_img_url = post_img_url;
    }
    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }
}
