package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
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

    @GetMapping("/")
    public String index(Model model) {
        List<Country> countries = countryService.findAll();
        Map<Country, Map<Championship, List<Team>>> countryChampionshipTeams = new LinkedHashMap<>();

        for (Country country : countries) {
            List<Championship> championships = championshipService.getChampionshipsByCountry(country);
            Map<Championship, List<Team>> championshipTeams = new HashMap<>();

            for (Championship c : championships) {
                // Récupérer les participations via TeamChampionship
                List<TeamChampionship> participations = teamChampionshipDao.findByChampionship_Id(c.getId());
                List<Team> teams = participations.stream()
                        .map(TeamChampionship::getTeam)
                        .collect(Collectors.toList());
                championshipTeams.put(c, teams);
            }

            countryChampionshipTeams.put(country, championshipTeams);
        }

        model.addAttribute("countryChampionshipTeams", countryChampionshipTeams);
        return "index";
    }

    @GetMapping("/championship/{championShipId}")
    public String showChampionShipResults(@PathVariable Long championShipId, Model model) {
        Championship championShip = championshipService.findById(championShipId);
        List<Game> games = gameService.getGamesByChampionship(championShipId);

        model.addAttribute("championShip", championShip);
        model.addAttribute("games", games);
        return "championship/results";
    }

    @GetMapping("/championship/{championShipId}/ranking")
    public String showChampionShipRanking(@PathVariable Long championShipId, Model model) {
        Championship championship = championshipService.findById(championShipId);
        List<Team> ranking = teamService.calculateRanking(championship);

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

        model.addAttribute("championship", championShip);
        model.addAttribute("day", day);
        model.addAttribute("games", games);
        return "championship/day_results";
    }
}
