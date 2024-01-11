package com.cars.CarLink.Model;

public class ClientDto {
    private String email;
    private String name;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String country;
    private String about;

    public ClientDto() {
    }

    public ClientDto(String email, String name, String phoneNumber,
                     String streetAddress, String city,
                     String country, String about) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.about=about;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbout() {
        return about;
    }
}
