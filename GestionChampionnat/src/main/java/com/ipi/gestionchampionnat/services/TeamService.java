package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.dto.TeamRankingDTO;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team team);
    void delete(Long id);
    void deleteAll();
    List<TeamRankingDTO> calculateRanking(Championship championShip);
}
