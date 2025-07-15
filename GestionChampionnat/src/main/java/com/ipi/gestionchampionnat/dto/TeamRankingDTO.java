package com.ipi.gestionchampionnat.dto;

import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;

public class TeamRankingDTO {
    private Team team;
    private int totalPoints;
    private int playedGames;
    private int wonGames;
    private int drawGames;
    private int lostGames;
    private int goalsFor;
    private int goalsAgainst;
    private List<String> lastResults;

    public TeamRankingDTO() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public int getDrawGames() {
        return drawGames;
    }

    public void setDrawGames(int drawGames) {
        this.drawGames = drawGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void setLostGames(int lostGames) {
        this.lostGames = lostGames;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public List<String> getLastResults() {
        return lastResults;
    }

    public void setLastResults(List<String> lastResults) {
        this.lastResults = lastResults;
    }
}
