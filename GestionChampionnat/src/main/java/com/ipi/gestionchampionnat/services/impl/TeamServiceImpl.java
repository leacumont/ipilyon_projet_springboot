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
    public List<Team> calculateRanking(Championship championShip) {
        Map<Team, Integer> teamPoints = new HashMap<>();
        List<Game> games = gameDao.findByChampionShip(championShip);

        for (Game game : games) {
            Team home = game.getTeam1();
            Team away = game.getTeam1();
            int homeScore = game.getTeam1Point();
            int awayScore = game.getTeam2Point();

            teamPoints.putIfAbsent(home, 0);
            teamPoints.putIfAbsent(away, 0);

            if (homeScore > awayScore) {
                teamPoints.put(home, teamPoints.get(home) + 3);
            } else if (homeScore < awayScore) {
                teamPoints.put(away, teamPoints.get(away) + 3);
            } else {
                teamPoints.put(home, teamPoints.get(home) + 1);
                teamPoints.put(away, teamPoints.get(away) + 1);
            }
        }

        List<Team> ranking = new ArrayList<>(teamPoints.keySet());
        ranking.sort((t1, t2) -> teamPoints.get(t2) - teamPoints.get(t1));

        return ranking;
    }
}
