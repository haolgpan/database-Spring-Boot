package com.example.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface countryDAO extends JpaRepository<Country,Integer> {
}
