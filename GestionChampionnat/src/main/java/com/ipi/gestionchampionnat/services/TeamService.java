package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team team);
    void delete(Long id);
    void deleteAll();
    List<Team> calculateRanking(Championship championShip);
    List<Team> getTeamsByCountry(Long countryId);
    List<Team> getTeamsByStadium(Long stadiumId);
    List<Team> getTeamsByChampionship(Long championshipId);

}
