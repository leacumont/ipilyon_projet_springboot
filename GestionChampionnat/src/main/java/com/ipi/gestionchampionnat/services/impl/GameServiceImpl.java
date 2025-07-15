package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameDao gameDao;

    @Override
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public Game findById(Long id) {
        return gameDao.findById(id).get();
    }

    @Override
    public Game save(Game game) {
        return gameDao.save(game);
    }

    @Override
    public void delete(Long id) {
        gameDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        gameDao.deleteAll();
    }

    @Override
    public List<Game> getGamesByDay(Long dayId) {
        return gameDao.findByDay_Id(dayId);
    }

    @Override
    public List<Game> getGamesByTeam(Long teamId) {
        return gameDao.findByTeam1_IdOrTeam2_Id(teamId, teamId);
    }

    @Override
    public List<Game> getGamesByChampionship(Long championshipId) {
        return gameDao.findByDay_Championship_Id(championshipId);
    }

    @Override
    public List<Game> getGamesByChampionshipAndTeam(Long championshipId, Long teamId) {
        return gameDao.findGamesByChampionshipAndTeam(championshipId, teamId);
    }

    @Override
    public Optional<Game> getGameBetweenTeams(Long team1Id, Long team2Id, Long championshipId) {
        return gameDao.findGameBetweenTeams(team1Id, team2Id, championshipId);
    }

    @Override
    public List<Game> getGamesByChampionshipOrderByDay(Long championshipId) {
        return gameDao.findGamesByChampionshipOrderByDay(championshipId);
    }
}
