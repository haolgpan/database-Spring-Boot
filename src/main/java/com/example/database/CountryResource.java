package com.example.database;


import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(CountryResource.COUNTRY)
public class CountryResource {
    public static final String COUNTRY = "/country";
    @Autowired
    CountryController countryController;


    @GetMapping
    public List<CountryDto> readAll(){
        return countryController.getAllCountry();
    }

    @GetMapping("{id}")
    public CountryDto getCountry(@PathVariable Integer id){
        return countryController.getCountryById(id);
    }

    @GetMapping("{id}/code")
    public Map<String,String> code(@PathVariable Integer id){
       return Collections.singletonMap("code",countryController.getCountryById(id).getCode());
    }

    @PostMapping("")
    public void addCountry(@RequestBody CountryDto countryDto){
        countryController.addCountry(countryDto);
    }

    @DeleteMapping("{id}/delete")
    public void deleteCountry(@PathVariable Integer id){
        countryController.deleteCountryById(id);
    }

    @PutMapping("{id}/update")
    public CountryDto updateCountry(@PathVariable Integer id, @RequestBody CountryDto countryDto) {
        CountryDto updatedCountry = countryController.updateCountryById(id, countryDto);
        return updatedCountry;
    }

    @PatchMapping("{id}/patch-update")
    public CountryDto partialUpdateCountry(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        CountryDto currentCountry = countryController.getCountryById(id);
        CountryDto patchedCountry = countryController.applyPatch(patch, currentCountry);
        countryController.updateCountryById(id, patchedCountry);
        return patchedCountry;
    }


}
