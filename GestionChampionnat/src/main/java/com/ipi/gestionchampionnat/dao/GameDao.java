package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {
    List<Game> findByDay_Championship_IdAndDay_Number(Long championshipId, String number);
}