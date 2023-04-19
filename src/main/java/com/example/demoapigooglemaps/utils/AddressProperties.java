package com.example.demoapigooglemaps.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="address")
public class AddressProperties {

    public String getOriginStreet() {
        return originStreet;
    }

    public String getOriginHeight() {
        return originHeight;
    }

    public String getOriginLocality() {
        return originLocality;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public String getDestinationHeight() {
        return destinationHeight;
    }

    public String getDestinationLocality() {
        return destinationLocality;
    }

    public void setOriginStreet(String originStreet) {
        this.originStreet = originStreet;
    }

    public void setOriginHeight(String originHeight) {
        this.originHeight = originHeight;
    }

    public void setOriginLocality(String originLocality) {
        this.originLocality = originLocality;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public void setDestinationHeight(String destinationHeight) {
        this.destinationHeight = destinationHeight;
    }

    public void setDestinationLocality(String destinationLocality) {
        this.destinationLocality = destinationLocality;
    }

    private String originStreet;
    private String originHeight;
    private String originLocality;
    private String destinationStreet;
    private String destinationHeight;
    private String destinationLocality;
}
