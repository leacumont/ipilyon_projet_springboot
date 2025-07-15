package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.dao.TeamDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;
import com.ipi.gestionchampionnat.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TeamChampionshipDao teamChampionshipDao;

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
        List<TeamChampionship> participations = teamChampionshipDao.findByChampionship_Id(championship.getId());

        // Map Team -> points calculés
        Map<Team, Integer> teamPointsMap = new HashMap<>();

        // Récupère les paramètres du championnat
        int pointsForWin = championship.getWonPoint();
        int pointsForDraw = championship.getDrawPoint();
        int pointsForLoss = championship.getLostPoint();

        for (TeamChampionship tc : participations) {
            Team team = tc.getTeam();

            // Calculer les stats de l'équipe dans ce championnat (via gameService ?)
            List<Game> games = gameDao.getGamesByTeamAndChampionship(team.getId(), championship.getId());

            int wins = 0, draws = 0, losses = 0;

            for (Game game : games) {
                // Il faut déterminer si l'équipe a gagné, perdu ou fait nul dans ce match
                if (game.getScoreHome() == null || game.getScoreAway() == null) {
                    // Match pas encore joué, on ignore
                    continue;
                }

                boolean isHomeTeam = game.getHomeTeam().getId().equals(team.getId());
                int teamScore = isHomeTeam ? game.getScoreHome() : game.getScoreAway();
                int opponentScore = isHomeTeam ? game.getScoreAway() : game.getScoreHome();

                if (teamScore > opponentScore) {
                    wins++;
                } else if (teamScore == opponentScore) {
                    draws++;
                } else {
                    losses++;
                }
            }

            int totalPoints = wins * pointsForWin + draws * pointsForDraw + losses * pointsForLoss;
            teamPointsMap.put(team, totalPoints);
        }

        // Trier les équipes par points décroissants
        return teamPointsMap.entrySet().stream()
                .sorted(Map.Entry.<Team, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }



    @Override
    public List<Team> getTeamsByCountry(Long countryId) {
        return teamDao.findByCountry_Id(countryId);
    }

    @Override
    public List<Team> getTeamsByStadium(Long stadiumId) {
        return teamDao.findByStadium_Id(stadiumId);
    }

    @Override
    public List<Team> getTeamsByChampionship(Long championshipId) {
        return teamDao.findTeamsByChampionshipId(championshipId);
    }
}
