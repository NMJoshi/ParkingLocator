package com.example.parkinglocator.Model;

public class ParkingOwner {
    String email;
    String name;
    String Address;
    String phoneNumber;
    String parkingOwnerId;

    public ParkingOwner(){

    }
    public ParkingOwner(String email, String name, String address, String phoneNumber, String parkingOwnerId) {
        this.email = email;
        this.name = name;
        Address = address;
        this.phoneNumber = phoneNumber;
        this.parkingOwnerId = parkingOwnerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParkingOwnerId() {
        return parkingOwnerId;
    }

    public void setParkingOwnerId(String parkingOwnerId) {
        this.parkingOwnerId = parkingOwnerId;
    }
}
