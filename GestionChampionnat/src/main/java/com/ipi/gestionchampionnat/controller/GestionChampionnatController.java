package com.ipi.gestionchampionnat.controller;


import com.ipi.gestionchampionnat.dao.ChampionshipDao;
import com.ipi.gestionchampionnat.dao.UserDao;
import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GestionChampionnatController {
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
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

    //CONNEXION
    @Autowired
    public GestionChampionnatController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/connexion")
    public String formConnexion() {
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connexion(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userDao.findByEmail(email); // <-- Optional
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("UserConnecte", user);
            return "redirect:/";
        } else {
            model.addAttribute("erreur", true);
            return "connexion";
        }
    }

    @GetMapping("/admin/dashboard")
    public String backoffice(Model model) {
        return "admin/dashboard";
    }

    // INDEX
    @GetMapping("/")
    public String index(Model model) {
        List<Championship> championnats = championshipService.findAll();
        List<Country> countries = countryService.findAllWithChampionShips();
        model.addAttribute("countries", countries);
        model.addAttribute("championnats", championnats);
        return "index";
    }

    @GetMapping("/championship/{championShipId}")
    public String showChampionShipResults(@PathVariable Long championShipId, Model model) {
        Championship championShip = championshipService.findById(championShipId);
        List<Game> games = gameService.findByChampionship(championShip);
        model.addAttribute("championShip", championShip);
        model.addAttribute("games", games);
        return "championship/results";
    }

    @GetMapping("/championship/{championShipId}/ranking")
    public String showChampionShipRanking(@PathVariable Long championshipId, Model model) {
        Championship championship = championshipService.findById(championshipId);
        List<Team> ranking = teamService.calculateRanking(championship);
        model.addAttribute("championShip", championship);
        model.addAttribute("ranking", ranking);
        return "championship/ranking";
    }

    @GetMapping("/team/{teamId}")
    public String showTeamSheet(@PathVariable Long teamId, Model model) {
        Team team = teamService.findById(teamId).get();
        List<Game> teamGames = gameService.findByTeam(team);
        model.addAttribute("team", team);
        model.addAttribute("games", teamGames);
        return "team/details";
    }

    @GetMapping("/championship/{championShipId}/day/{dayId}")
    public String showDayResults(@PathVariable Long championShipId,
                                 @PathVariable Long dayId,
                                 Model model) {
        Championship championShip = championshipService.findById(championShipId);
        Day day = dayService.findById(dayId);
        List<Game> games = gameService.findByDay(day);

        model.addAttribute("championship", championShip);
        model.addAttribute("day", day);
        model.addAttribute("games", games);
        return "championship/day_results";
    }

    @GetMapping("/team/{id}")
    public String showTeamDetails(@PathVariable Long id, Model model) {
        Team team = teamService.findById(id).get();
        model.addAttribute("team", team);
        return "team/details";
    }
}
