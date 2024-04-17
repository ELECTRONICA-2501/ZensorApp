package com.example.zensorapp;

import com.google.firebase.Timestamp;

public class Zensor {

    private String meditation;
    private String emotion;
    private String pageTitle;
    private String pageContent;
    private String imageURL;
    private String userID;
    private Timestamp timeAdded;
    private String userName;

    public Zensor() {
    }

    public Zensor(String meditation, String emotion, String pageTitle, String pageContent, String imageURL, String userID, Timestamp timeAdded, String userName) {
        this.meditation = meditation;
        this.emotion = emotion;
        this.pageTitle = pageTitle;
        this.pageContent = pageContent;
        this.imageURL = imageURL;
        this.userID = userID;
        this.timeAdded = timeAdded;
        this.userName = userName;
    }

    public String getMeditation() {
        return meditation;
    }

    public void setMeditation(String meditation) {
        this.meditation = meditation;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
