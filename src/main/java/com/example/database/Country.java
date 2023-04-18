package com.example.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "country")
public class Country implements Serializable {
    @Id
    @Column(name = "id_country")
    int countryId;
    @Column(name = "code", length = 2)
    String code;
    @Column(name = "name", length = 30)
    String name;
    @Column(name = "latitude")
    float latitude;
    @Column(name = "longitud")
    float longitud;

    public Country() {
    }

    public Country(int countryId, String code, String name, float latitude, float longitud) {
        this.countryId = countryId;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitud = longitud;
    }
    public Country(CountryDto countryDto){
        this.countryId = countryDto.getCountryId();
        this.code = countryDto.getCode();
        this.name = countryDto.getName();
        this.latitude = countryDto.getLatitude();
        this.longitud = countryDto.getLongitud();
    }
}
