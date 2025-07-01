package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {
    List<Game> findByDay_Championship_IdAndDay_Number(Long championshipId, String number);
    List<Game> findByChampionShip(Championship championShip);
    List<Game> findByHomeTeamOrAwayTeam(Team home, Team away);
}