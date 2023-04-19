package com.example.demoapigooglemaps.model;

import java.math.BigDecimal;

public class Coordenate {

    private BigDecimal latitude;
    private BigDecimal longitude;
    public Coordenate(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
    @Override
    public String toString() {
        return this.longitude.toString().concat(",").concat(latitude.toString());
    }
}
