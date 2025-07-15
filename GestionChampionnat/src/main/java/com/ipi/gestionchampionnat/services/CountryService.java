package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country save(Country country);
    void delete(Long id);
    void deleteAll();
    List<Country> getCountriesWithTeams();
    List<Country> getCountriesOrderByTeamsCount();
}
