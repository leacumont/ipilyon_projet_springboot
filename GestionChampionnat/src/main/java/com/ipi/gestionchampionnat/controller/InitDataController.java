package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.dao.*;
import com.ipi.gestionchampionnat.pojos.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class InitDataController {
    private StadiumDao stadiumDao;
    private TeamDao teamDao;
    private CountryDao countryDao;
    private ChampionshipDao championshipDao;
    private DayDao dayDao;
    private GameDao gameDao;
    private TeamChampionshipDao teamChampionshipDao;
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitDataController(StadiumDao stadiumDao, TeamDao teamDao, CountryDao countryDao, ChampionshipDao championshipDao, DayDao dayDao, GameDao gameDao, TeamChampionshipDao teamChampionshipDao, UserDao userDao) {
        this.stadiumDao = stadiumDao;
        this.teamDao = teamDao;
        this.countryDao = countryDao;
        this.championshipDao = championshipDao;
        this.dayDao = dayDao;
        this.gameDao = gameDao;
        this.teamChampionshipDao = teamChampionshipDao;
        this.userDao = userDao;
    }

    @PostConstruct
    protected void init() {
        try {gameDao.deleteAll();
        teamChampionshipDao.deleteAll();
        teamDao.deleteAll();
        dayDao.deleteAll();
        championshipDao.deleteAll();
        countryDao.deleteAll();
        stadiumDao.deleteAll();
        userDao.deleteAll();

        Championship championship = new Championship();
        championship.setName("Ligue 1");
        championship.setLogo("https://example.com/logos/ligue1.png");
        championship.setStartDate(LocalDate.of(2025, 8, 1));
        championship.setEndDate(LocalDate.of(2026, 5, 31));
        championship.setWonPoint(3);
        championship.setLostPoint(0);
        championship.setDrawPoint(1);
        championship.setTypeRanking("points");

        Country france = new Country();
        france.setName("France");
        france.setLogo("https://example.com/logos/france.png");
        france.setTeams(new ArrayList<>());

        Day day1 = new Day();
        day1.setNumber("1");
        day1.setChampionship(championship);
        day1.setGames(new ArrayList<>());

        Day day2 = new Day();
        day2.setNumber("2");
        day2.setChampionship(championship);
        day2.setGames(new ArrayList<>());

        Team psg = new Team();
        psg.setName("Paris Saint-Germain");
        psg.setCreationDate(LocalDate.of(1970, 8, 12));
        psg.setLogo("https://example.com/logos/psg.png");
        psg.setCoach("Luis Enrique");
        psg.setPresident("Nasser Al-Khelaïfi");
        psg.setStatus("Pro");
        psg.setSiege("Paris");
        psg.setPhone("0123456789");
        psg.setWebSite("https://psg.fr");
        psg.setCountry(france);
        psg.setTeam1(new ArrayList<>());
        psg.setTeam2(new ArrayList<>());
        psg.setTeamChampionships(new ArrayList<>());

        Team om = new Team();
        om.setName("Olympique de Marseille");
        om.setCreationDate(LocalDate.of(1899, 8, 31));
        om.setLogo("https://example.com/logos/om.png");
        om.setCoach("Jean-Louis Gasset");
        om.setPresident("Pablo Longoria");
        om.setStatus("Pro");
        om.setSiege("Marseille");
        om.setPhone("0491753230");
        om.setWebSite("https://om.fr");
        om.setCountry(france);
        om.setTeam1(new ArrayList<>());
        om.setTeam2(new ArrayList<>());
        om.setTeamChampionships(new ArrayList<>());

        france.getTeams().add(psg);
        france.getTeams().add(om);

        Game match1 = new Game();
        match1.setTeam1(psg);
        match1.setTeam2(om);
        match1.setTeam1Point(2);
        match1.setTeam2Point(1);
        match1.setDay(day1);

        Game match2 = new Game();
        match2.setTeam1(psg);
        match2.setTeam2(om);
        match2.setTeam1Point(4);
        match2.setTeam2Point(4);
        match2.setDay(day2);

        psg.getTeam1().add(match1);
        psg.getTeam1().add(match2);
        om.getTeam2().add(match1);
        om.getTeam2().add(match2);

        day1.getGames().add(match1);
        day2.getGames().add(match2);

        championship.setCountry(france);

        TeamChampionship teamChampionshipPSG = new TeamChampionship();
        teamChampionshipPSG.setChampionship(championship);
        teamChampionshipPSG.setTeam(psg);

        TeamChampionship teamChampionshipOM = new TeamChampionship();
        teamChampionshipOM.setChampionship(championship);
        teamChampionshipOM.setTeam(om);

        championship.setTeamChampionships(new ArrayList<>());
        championship.getTeamChampionships().add(teamChampionshipOM);
        championship.getTeamChampionships().add(teamChampionshipPSG);

        psg.getTeamChampionships().add(teamChampionshipPSG);
        om.getTeamChampionships().add(teamChampionshipOM);

        championship.setDays(new ArrayList<>());
        championship.getDays().add(day1);
        championship.getDays().add(day2);

        Stadium parcDesPrinces = new Stadium();
        parcDesPrinces.setName("Parc des Princes");
        parcDesPrinces.setAddress("24 Rue du Commandant Guilbaud, 75016 Paris");
        parcDesPrinces.setCapacity(47929);
        parcDesPrinces.setPhone("+33 1 47 43 71 71");
        parcDesPrinces.setTeams(new ArrayList<>());

        psg.setStadium(parcDesPrinces);
        om.setStadium(parcDesPrinces);

        parcDesPrinces.getTeams().add(psg);
        parcDesPrinces.getTeams().add(om);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword(passwordEncoder.encode("securePassword123"));
        user.setCreationDate(LocalDate.now());
        user.setRole(Role.USER);

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("securePasswordADMIN"));
        admin.setCreationDate(LocalDate.now());
        admin.setRole(Role.ADMIN);

        stadiumDao.save(parcDesPrinces);
        countryDao.save(france);
        championshipDao.save(championship);
        dayDao.save(day1);
        dayDao.save(day2);
        teamDao.save(psg);
        teamDao.save(om);
        gameDao.save(match1);
        gameDao.save(match2);
        teamChampionshipDao.save(teamChampionshipPSG);
        teamChampionshipDao.save(teamChampionshipOM);
        userDao.save(user);
        userDao.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
