package com.onlinemarketplace.model;

public class Message {
    int sender;
    int recv;
    int ad;
    String message;

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecv() {
        return recv;
    }

    public void setRecv(int recv) {
        this.recv = recv;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
