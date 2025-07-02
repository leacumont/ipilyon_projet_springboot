package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.dao.TeamDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    GameDao gameDao;

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamDao.findById(id);
    }

    @Override
    public Team save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public void delete(Long id) {
        teamDao.delete(teamDao.findById(id).get());
    }

    @Override
    public void deleteAll() { teamDao.deleteAll(); }

    @Override
    public List<Team> calculateRanking(Championship championship) {
        Map<Team, Integer> pointsMap = new HashMap<>();
        List<Game> games = gameDao.findByChampionShip(championship);

        for (Game game : games) {
            Team team1 = game.getTeam1();
            Team team2 = game.getTeam2();
            int team1Points = game.getTeam1Point();
            int team2Points = game.getTeam2Point();

            pointsMap.putIfAbsent(team1, 0);
            pointsMap.putIfAbsent(team2, 0);

            if (team1Points > team2Points) {
                pointsMap.put(team1, pointsMap.get(team1) + 3);
            } else if (team1Points < team2Points) {
                pointsMap.put(team2, pointsMap.get(team2) + 3);
            } else {
                pointsMap.put(team1, pointsMap.get(team1) + 1);
                pointsMap.put(team2, pointsMap.get(team2) + 1);
            }
        }

        return pointsMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Team, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }
}
