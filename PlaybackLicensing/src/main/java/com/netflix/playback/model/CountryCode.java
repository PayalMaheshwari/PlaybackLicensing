package com.netflix.playback.model;

public enum CountryCode {

    USA("US", "United States"),
    CA("CAN", "Canada"),
    IND("IND", "India"),
    AU("AUS", "Australia"),
    GB("GBR", "United Kingdom");

    private String code;
    private String name;

     CountryCode() {
    }

    CountryCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
