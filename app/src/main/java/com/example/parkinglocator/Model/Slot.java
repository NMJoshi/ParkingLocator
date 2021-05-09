package com.example.parkinglocator.Model;


import java.io.Serializable;

public class Slot implements Serializable {



    String id;
    String availablity;
    Long number;
    String type;

    public Slot(){

    }

    public Slot(String id, String availablity, Long number, String type) {
        this.id = id;
        this.availablity = availablity;
        this.number = number;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailablity() {
        return availablity;
    }

    public void setAvailablity(String availablity) {
        this.availablity = availablity;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
