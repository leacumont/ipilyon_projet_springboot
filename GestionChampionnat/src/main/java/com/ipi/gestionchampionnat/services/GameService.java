package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> findAll();
    Game findById(Long id);
    Game save(Game game);
    void delete(Long id);
    void deleteAll();
    List<Game> getGamesByDay(Long dayId);
    List<Game> getGamesByTeam(Long teamId);
    List<Game> findByDay_Championship_Id(Long championshipId);
}
