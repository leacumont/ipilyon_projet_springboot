package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int wonPoint;
    private int lostPoint;
    private int drawPoint;
    private String typeRanking;

    @OneToMany(mappedBy = "championship")
    private List<Day> days;

    @OneToMany(mappedBy = "championship")
    private List<TeamChampionship> teamChampionships;

    private List<Team> teams;

    public Championship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(int wonPoint) {
        this.wonPoint = wonPoint;
    }

    public int getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(int lostPoint) {
        this.lostPoint = lostPoint;
    }

    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
        this.drawPoint = drawPoint;
    }

    public String getTypeRanking() {
        return typeRanking;
    }

    public void setTypeRanking(String typeRanking) {
        this.typeRanking = typeRanking;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<TeamChampionship> getTeamChampionships() {
        return teamChampionships;
    }

    public void setTeamChampionships(List<TeamChampionship> teamChampionships) {
        this.teamChampionships = teamChampionships;
    }

    @Override
    public String toString() {
        return "Championship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", wonPoint=" + wonPoint +
                ", lostPoint=" + lostPoint +
                ", drawPoint=" + drawPoint +
                ", typeRanking='" + typeRanking + '\'' +
                ", days=" + days +
                ", teams=" + teams +
                '}';
    }
}
