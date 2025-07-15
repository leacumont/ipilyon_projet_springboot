package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate creationDate;
    private String logo;
    private String coach;
    private String president;
    private String status;
    private String siege;
    private String phone;
    private String webSite;

    @ManyToOne
    @JoinColumn(name = "idStadium")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "idCountry")
    private Country country;

    @OneToMany(mappedBy = "team1")
    private List<Game> team1;

    @OneToMany(mappedBy = "team2")
    private List<Game> team2;

    @OneToMany(mappedBy = "team")
    private List<TeamChampionship> teamChampionships;

    public Team() {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Game> getTeam1() {
        return team1;
    }

    public void setTeam1(List<Game> team1) {
        this.team1 = team1;
    }

    public List<Game> getTeam2() {
        return team2;
    }

    public void setTeam2(List<Game> team2) {
        this.team2 = team2;
    }

    public List<TeamChampionship> getTeamChampionships() {
        return teamChampionships;
    }

    public void setTeamChampionships(List<TeamChampionship> teamChampionships) {
        this.teamChampionships = teamChampionships;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", logo='" + logo + '\'' +
                ", coach='" + coach + '\'' +
                ", president='" + president + '\'' +
                ", status='" + status + '\'' +
                ", siege='" + siege + '\'' +
                ", phone='" + phone + '\'' +
                ", webSite='" + webSite + '\'' +
                ", stadium=" + stadium +
                ", country=" + country +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", teamChampionships=" + teamChampionships +
                '}';
    }
}
