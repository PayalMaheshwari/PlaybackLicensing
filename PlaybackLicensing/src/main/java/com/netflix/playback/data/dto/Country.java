package com.netflix.playback.data.dto;

//@Entity
public class Country {

   // @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long countryId;

    private String countryCode;
    private String countryName;

    public Country() {
    }

    public Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public Long getCountryId() {
        return countryId;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
