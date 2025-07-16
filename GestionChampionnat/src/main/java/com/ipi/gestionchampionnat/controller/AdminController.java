package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @Autowired
    private DayService dayService;

    @Autowired
    private StadiumService stadiumService;

    @GetMapping("/dashboard")
    public String showCreatePage(Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("championships", championshipService.findAll());
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("days", dayService.findAll());
        return "admin/dashboard";
    }

    @PostMapping("/championship/create")
    public String createChampionship(@RequestParam String name,
                                     @RequestParam String logo,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     @RequestParam int wonPoint,
                                     @RequestParam int lostPoint,
                                     @RequestParam int drawPoint,
                                     @RequestParam String typeRanking,
                                     @RequestParam Long countryId) {
        Country country = countryService.findById(countryId);

        Championship championship = new Championship();
        championship.setName(name);
        championship.setLogo(logo);
        championship.setStartDate(startDate);
        championship.setEndDate(endDate);
        championship.setWonPoint(wonPoint);
        championship.setLostPoint(lostPoint);
        championship.setDrawPoint(drawPoint);
        championship.setTypeRanking(typeRanking);
        championship.setCountry(country);

        championshipService.save(championship);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/team/create")
    public String createTeam(@RequestParam String name,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate,
                             @RequestParam String logo,
                             @RequestParam String coach,
                             @RequestParam String president,
                             @RequestParam String status,
                             @RequestParam String siege,
                             @RequestParam String phone,
                             @RequestParam String webSite,
                             @RequestParam Long stadiumId,
                             @RequestParam Long countryId) {
        Stadium stadium = stadiumService.findById(stadiumId);
        Country country = countryService.findById(countryId);

        Team team = new Team();
        team.setName(name);
        team.setCreationDate(creationDate);
        team.setLogo(logo);
        team.setCoach(coach);
        team.setPresident(president);
        team.setStatus(status);
        team.setSiege(siege);
        team.setPhone(phone);
        team.setWebSite(webSite);
        team.setStadium(stadium);
        team.setCountry(country);

        teamService.save(team);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/game/create")
    public String createGame(@RequestParam Long team1Id,
                             @RequestParam Long team2Id,
                             @RequestParam Long dayId,
                             @RequestParam int score1,
                             @RequestParam int score2) {
        Game game = new Game();
        game.setTeam1(teamService.findById(team1Id).get());
        game.setTeam2(teamService.findById(team2Id).get());
        game.setDay(dayService.findById(dayId));
        game.setTeam1Point(score1);
        game.setTeam2Point(score2);
        gameService.save(game);
        return "redirect:/admin/dashboard";
    }
}
