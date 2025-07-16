package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;

import java.util.List;
import java.util.Optional;

public interface TeamChampionshipService {
    List<TeamChampionship> findAll();
    Optional<TeamChampionship> findById(Long id);
    TeamChampionship save(TeamChampionship TeamChampionship);
    void delete(Long id);
    void deleteAll();
}
