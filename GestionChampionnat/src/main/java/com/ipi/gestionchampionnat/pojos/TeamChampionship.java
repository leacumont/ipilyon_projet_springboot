package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

@Entity
public class TeamChampionship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idChampionship", nullable = false)
    private Championship championship;

    @ManyToOne
    @JoinColumn(name = "idTeam", nullable = false)
    private Team team;

    // Constructeurs
    public TeamChampionship() {}

    public TeamChampionship(Championship championship, Team team) {
        this.championship = championship;
        this.team = team;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Championship getChampionship() { return championship; }
    public void setChampionship(Championship championship) { this.championship = championship; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
