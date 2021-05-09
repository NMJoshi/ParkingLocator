package com.example.parkinglocator.Model;

import java.io.Serializable;

public class ParkingDetail implements Serializable {

    String name;
    String email;
    String phonenumber;
    String address;
     String twoWheeler;
     String fourWheeler;
     String latitude;
     String longitude;
     String parkingDetailId;
     String ownerId;


 public String getOwnerId() {
  return ownerId;
 }

 public void setOwnerId(String ownerId) {
  this.ownerId = ownerId;
 }

 public ParkingDetail(String name, String email, String phonenumber, String address, String twoWheeler, String fourWheeler, String latitude, String longitude, String parkingDetailId, String ownerId) {
  this.name = name;
  this.email = email;
  this.phonenumber = phonenumber;
  this.address = address;
  this.twoWheeler = twoWheeler;
  this.fourWheeler = fourWheeler;
  this.latitude = latitude;
  this.longitude = longitude;
  this.parkingDetailId = parkingDetailId;
  this.ownerId = ownerId;
 }

 public  ParkingDetail(){

     }


 public ParkingDetail(String name, String email, String phonenumber, String address, String twoWheeler, String fourWheeler, String latitude, String longitude, String parkingDetailId) {
  this.name = name;
  this.email = email;
  this.phonenumber = phonenumber;
  this.address = address;
  this.twoWheeler = twoWheeler;
  this.fourWheeler = fourWheeler;
  this.latitude = latitude;
  this.longitude = longitude;
  this.parkingDetailId = parkingDetailId;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPhonenumber() {
  return phonenumber;
 }

 public void setPhonenumber(String phonenumber) {
  this.phonenumber = phonenumber;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public String getTwoWheeler() {
  return twoWheeler;
 }

 public void setTwoWheeler(String twoWheeler) {
  this.twoWheeler = twoWheeler;
 }

 public String getFourWheeler() {
  return fourWheeler;
 }

 public void setFourWheeler(String fourWheeler) {
  this.fourWheeler = fourWheeler;
 }

 public String getLatitude() {
  return latitude;
 }

 public void setLatitude(String latitude) {
  this.latitude = latitude;
 }

 public String getLongitude() {
  return longitude;
 }

 public void setLongitude(String longitude) {
  this.longitude = longitude;
 }

 public String getParkingDetailId() {
  return parkingDetailId;
 }

 public void setParkingDetailId(String parkingDetailId) {
  this.parkingDetailId = parkingDetailId;
 }
}
