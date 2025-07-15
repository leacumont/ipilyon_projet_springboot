package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.dto.TeamRankingDTO;
import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ChampionshipController {
    @Autowired
    private ChampionshipService championshipService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private DayService dayService;
    @Autowired
    private TeamChampionshipDao teamChampionshipDao;

    @Autowired
    public ChampionshipController(ChampionshipService championshipService, TeamService teamService, GameService gameService, CountryService countryService, DayService dayService, TeamChampionshipDao teamChampionshipDao) {
        this.championshipService = championshipService;
        this.teamService = teamService;
        this.gameService = gameService;
        this.countryService = countryService;
        this.dayService = dayService;
        this.teamChampionshipDao = teamChampionshipDao;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> errors = new ArrayList<>();
        List<Country> countries = countryService.findAll();
        if (countries == null || countries.isEmpty()) {
            errors.add("Aucun pays trouvé.");
            model.addAttribute("errors", errors);
            return "index";
        }
        Map<Country, Map<Championship, List<Team>>> countryChampionshipTeams = new LinkedHashMap<>();

        for (Country country : countries) {
            List<Championship> championships = championshipService.getChampionshipsByCountry(country);
            if (championships == null || championships.isEmpty()) {
                errors.add("Aucun championnat trouvé pour le pays : " + country.getName());
                continue;
            }
            Map<Championship, List<Team>> championshipTeams = new HashMap<>();

            for (Championship c : championships) {
                List<TeamChampionship> participations = teamChampionshipDao.findByChampionship_Id(c.getId());
                List<Team> teams = participations.stream()
                        .map(TeamChampionship::getTeam)
                        .collect(Collectors.toList());

                if (teams == null || teams.isEmpty()) {
                    errors.add("Aucune équipe trouvée pour le championnat : " + c.getName());
                }
                championshipTeams.put(c, teams);
            }
            countryChampionshipTeams.put(country, championshipTeams);
        }
        if (countryChampionshipTeams.isEmpty()) {
            errors.add("Aucun championnat ou équipe trouvé.");
        }
        model.addAttribute("countryChampionshipTeams", countryChampionshipTeams);
        model.addAttribute("errors", errors);
        return "index";
    }

    @GetMapping("/championship/{championShipId}")
    public String showChampionShipResults(@PathVariable Long championShipId, Model model) {
        Championship championShip = championshipService.findById(championShipId);
        List<Game> games = gameService.findByDay_Championship_Id(championShipId);

        Long lastDayId = championshipService.getLastDayId(championShipId);
        model.addAttribute("lastDayId", lastDayId);
        model.addAttribute("championShip", championShip);
        model.addAttribute("games", games);
        return "championship/results";
    }

    @GetMapping("/championship/{championShipId}/ranking")
    public String showChampionShipRanking(@PathVariable Long championShipId, Model model) {
        Championship championship = championshipService.findById(championShipId);
        List<TeamRankingDTO> ranking = teamService.calculateRanking(championship);

        model.addAttribute("championShip", championship);
        model.addAttribute("ranking", ranking);
        return "championship/ranking";
    }

    @GetMapping("/championship/{championShipId}/day/{dayId}")
    public String showDayResults(@PathVariable Long championShipId,
                                 @PathVariable Long dayId,
                                 Model model) {
        Championship championShip = championshipService.findById(championShipId);
        Day day = dayService.findById(dayId);
        List<Game> games = gameService.getGamesByDay(dayId);

        model.addAttribute("championShip", championShip);
        model.addAttribute("day", day);
        model.addAttribute("games", games);
        return "championship/day_results";
    }
}
