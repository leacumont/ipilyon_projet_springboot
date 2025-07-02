package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;

public interface ChampionshipService {
    List<Championship> findAll();
    Championship findById(Long id);
    Championship save(Championship championship);
    void delete(Long id);
    void deleteAll();

    /* méthodes métier utiles */
    void addTeam(Championship championship, Team team);
    List<Game> getGamesForDay(Long championshipId, Long dayId);
    List<Championship> findByCountry(Country country);
}
