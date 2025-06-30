package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int team1Point;
    private int team2Point;

    @ManyToOne
    @JoinColumn(name = "idTeam1")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "idTeam2")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "idDay")
    private Day day;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(int team1Point) {
        this.team1Point = team1Point;
    }

    public int getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(int team2Point) {
        this.team2Point = team2Point;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", team1Point=" + team1Point +
                ", team2Point=" + team2Point +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", day=" + day +
                '}';
    }
}
