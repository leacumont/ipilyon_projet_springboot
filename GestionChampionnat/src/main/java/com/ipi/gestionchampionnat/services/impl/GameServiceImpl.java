package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Game> findByChampionship(Championship championShip) {
        return gameDao.findByChampionShip(championShip);
    }

    @Override
    public List<Game> findByTeam(Team team) {
        return gameDao.findByHomeTeamOrAwayTeam(team, team);
    }


}
