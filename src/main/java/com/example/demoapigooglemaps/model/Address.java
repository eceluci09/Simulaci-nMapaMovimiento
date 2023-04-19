package com.example.demoapigooglemaps.model;

public class Address {

    public Address(String street, String height, String locality) {
        this.street = street;
        this.height = height;
        this.locality = locality;
        this.country = "Argentina";
    }

    private String street;
    private String height;
    private String locality;
    private String country;
    private Coordenate coordenate;

    public String getStreet() {
        return street;
    }

    public String getHeight() {
        return height;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    public Coordenate getCoordenate() {
        return coordenate;
    }

    public void setCoordenate(Coordenate coordenate) {
        this.coordenate = coordenate;
    }

    @Override
    public String toString() {
        return height.concat(" ").concat(street).concat(",").concat(locality).concat(",").concat(country);
    }
}
