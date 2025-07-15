package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.dao.TeamDao;
import com.ipi.gestionchampionnat.dto.TeamRankingDTO;
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
    public List<TeamRankingDTO> calculateRanking(Championship championship) {
        List<TeamChampionship> participations = teamChampionshipDao.findByChampionship_Id(championship.getId());
        List<Game> allGames = gameDao.findByDay_Championship_Id(championship.getId());

        int pointsForWin = championship.getWonPoint();
        int pointsForDraw = championship.getDrawPoint();
        int pointsForLoss = championship.getLostPoint();

        List<TeamRankingDTO> rankings = new ArrayList<>();

        for (TeamChampionship tc : participations) {
            Team team = tc.getTeam();
            TeamRankingDTO dto = new TeamRankingDTO();
            dto.setTeam(team);

            int points = 0, played = 0, won = 0, draw = 0, lost = 0, gf = 0, ga = 0;
            List<String> lastResults = new ArrayList<>();

            for (Game game : allGames) {
                if (game.getTeam1() == null || game.getTeam2() == null) continue;

                boolean isTeam1 = game.getTeam1().getId().equals(team.getId());
                boolean isTeam2 = game.getTeam2().getId().equals(team.getId());

                if (!isTeam1 && !isTeam2) continue;

                Integer teamScore = isTeam1 ? game.getTeam1Point() : game.getTeam2Point();
                Integer opponentScore = isTeam1 ? game.getTeam2Point() : game.getTeam1Point();

                if (teamScore == null || opponentScore == null) continue;

                gf += teamScore;
                ga += opponentScore;
                played++;

                if (teamScore > opponentScore) {
                    won++;
                    points += pointsForWin;
                    lastResults.add("W");
                } else if (teamScore.equals(opponentScore)) {
                    draw++;
                    points += pointsForDraw;
                    lastResults.add("D");
                } else {
                    lost++;
                    points += pointsForLoss;
                    lastResults.add("L");
                }
            }
            if (lastResults.size() > 5) {
                lastResults = lastResults.subList(lastResults.size() - 5, lastResults.size());
            }

            dto.setTotalPoints(points);
            dto.setPlayedGames(played);
            dto.setWonGames(won);
            dto.setDrawGames(draw);
            dto.setLostGames(lost);
            dto.setGoalsFor(gf);
            dto.setGoalsAgainst(ga);
            dto.setLastResults(lastResults);
            rankings.add(dto);
        }

        return rankings.stream()
                    .sorted(Comparator.comparingInt(TeamRankingDTO::getTotalPoints).reversed())
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
