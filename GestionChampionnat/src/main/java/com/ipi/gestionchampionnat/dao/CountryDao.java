package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
    @Query("SELECT c FROM Country c WHERE SIZE(c.teams) > 0")
    List<Country> findCountriesWithTeams();

    @Query("SELECT c FROM Country c ORDER BY SIZE(c.teams) DESC")
    List<Country> findCountriesOrderByTeamsCount();
}