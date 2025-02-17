package com.example.restdog;

public class DogImage {
    private String message;
    private String status;

    public DogImage(String image, String status) {
        this.message = image;
        this.status = status;
    }

    public String getImage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
