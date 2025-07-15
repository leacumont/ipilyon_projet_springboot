package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {
    List<Game> findByDay_Championship_Id(Long championshipId);

    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId AND (g.team1.id = :teamId OR g.team2.id = :teamId)")
    List<Game> findGamesByChampionshipAndTeam(@Param("championshipId") Long championshipId, @Param("teamId") Long teamId);

    @Query("SELECT g FROM Game g WHERE g.team1.id = :team1Id AND g.team2.id = :team2Id AND g.day.championship.id = :championshipId")
    Optional<Game> findGameBetweenTeams(@Param("team1Id") Long team1Id, @Param("team2Id") Long team2Id, @Param("championshipId") Long championshipId);

    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId ORDER BY g.day.number ASC")
    List<Game> findGamesByChampionshipOrderByDay(@Param("championshipId") Long championshipId);

    List<Game> findByTeam1_IdOrTeam2_Id(Long team1Id, Long team2Id);

    List<Game> findByDay_Id(Long dayId);
}