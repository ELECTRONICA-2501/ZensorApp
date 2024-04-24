package com.example.zensorapp;

import com.google.firebase.Timestamp;

public class Zensor {

    private String meditation;
    private String emotion;
    private String sensorData;
    private Timestamp timestamp;
    private String BPM;
    private String oxygen;
    private String perspiration;


    public Zensor() {
    }

    public Zensor(String meditation, String emotion, String sensorData, Timestamp timestamp, String BPM, String oxygen, String perspiration) {
        this.meditation = meditation;
        this.emotion = emotion;
        this.sensorData = sensorData;
        this.timestamp = timestamp;
        this.BPM = BPM;
        this.oxygen = oxygen;
        this.perspiration = perspiration;
    }

}