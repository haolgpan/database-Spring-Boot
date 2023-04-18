package com.example.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CountryController {
    @Autowired
    CountryService countryService;
    @Autowired
    public ObjectMapper objectMapper; // Inject ObjectMapper using @Autowired
    public List<CountryDto> getAllCountry() {
        List<Country> countries = countryService.findAll();
//        List<CountryDto> countryDtos = countries.stream().map(CountryDto::new).toList();
        List<CountryDto> countryDtos = countries.stream().map(country -> new CountryDto(country)).toList();
        return countryDtos;
    }

    public CountryDto getCountryById(Integer id) {

        Country country = countryService.getCountryById(id);
        return new CountryDto(country);
    }

    public void addCountry(CountryDto countryDto) {
        Country country = new Country();
        country.setCountryId(countryDto.getCountryId());
        country.setName(countryDto.getName());
        country.setCode(countryDto.getCode());
        country.setLatitude(countryDto.getLatitude());
        country.setLongitud(countryDto.getLongitud());
        // Call the service to add the country
        countryService.addCountry(country);
    }

    public void deleteCountryById(Integer id) {
        countryService.deleteCountryById(id);
    }

    public CountryDto updateCountryById(Integer id, CountryDto countryDto) {
        Country existingCountry = countryService.getCountryById(id); // Get the existing country by ID
        if (existingCountry != null) {
            // Update the existing country with the data from the countryDto
            existingCountry.setName(countryDto.getName());
            existingCountry.setCode(countryDto.getCode());
            existingCountry.setLatitude(countryDto.getLatitude());
            existingCountry.setLongitud(countryDto.getLongitud());
            // Call the service to save the updated country
            Country updatedCountry = countryService.updateCountry(existingCountry);
            // Return the updated country as a CountryDto
            return new CountryDto(updatedCountry);
        } else {
            throw new RuntimeException("Country with ID " + id + " not found.");
        }
    }
    public CountryDto applyPatch(JsonPatch patch, CountryDto currentCountry) {
        try {
            JsonNode patched = patch.apply(objectMapper.convertValue(currentCountry, JsonNode.class));
            return objectMapper.treeToValue(patched, CountryDto.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException("Failed to apply patch", e);
        }
    }
}
