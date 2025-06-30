package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "idChampionship")
    private Championship championship;

    @OneToMany(mappedBy = "day")
    private List<Game> games;

    public Day() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", championship=" + championship +
                ", games=" + games +
                '}';
    }
}
