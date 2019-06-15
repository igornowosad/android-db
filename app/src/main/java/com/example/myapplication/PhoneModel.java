package com.example.myapplication;

public class PhoneModel {
    private int phoneId;
    private String producer;
    private String model;
    private String androidVersion;
    private String url;

    public PhoneModel(int phoneId, String producer, String model, String androidVersion, String url) {
        this.phoneId = phoneId;
        this.producer = producer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.url = url;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
