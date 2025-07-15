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

    List<Game> findByTeamId(Long teamId);

    List<Game> findByDay_Id(Long dayId);

    List<Game> findByChampionshipIdAndTeamId(Long championshipId, Long teamId);
}