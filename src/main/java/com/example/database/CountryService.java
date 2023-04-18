package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    countryDAO repo;
    public List<Country> findAll() {
        return repo.findAll();
    }

    public Country getCountryById(Integer id) {
        Optional<Country> optionalCountry;
        optionalCountry = repo.findById(id);
        if(optionalCountry.isPresent()) return optionalCountry.get();
        else return null;
    }
    public Country updateCountry(Country country){
        return repo.save(country);
    }

    public void addCountry(Country country) {
        repo.save(country);
    }
    public void deleteCountryById(Integer id) {
        repo.deleteById(id);
    }
}
