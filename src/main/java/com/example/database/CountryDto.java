package com.example.database;

import lombok.Data;

@Data
public class CountryDto {

    private int countryId;
    private String code;
    private String name;
    private float latitude;
    private float longitud;

    public CountryDto(int countryId, String code, String name, float latitude, float longitud) {
        this.countryId = countryId;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitud = longitud;
    }
    public CountryDto(Country country){
        this.countryId = country.getCountryId();
        this.code = country.getCode();
        this.name = country.getName();
        this.latitude = country.getLatitude();
        this.longitud = country.getLongitud();
    }
}
