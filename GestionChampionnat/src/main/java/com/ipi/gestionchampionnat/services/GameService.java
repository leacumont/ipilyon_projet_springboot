package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;

public interface GameService {
    List<Game> findAll();
    Game findById(Long id);
    Game save(Game game);
    void delete(Long id);
    void deleteAll();
    List<Game> findByChampionship(Championship championShip);
    List<Game> findByTeam(Team team);
}
