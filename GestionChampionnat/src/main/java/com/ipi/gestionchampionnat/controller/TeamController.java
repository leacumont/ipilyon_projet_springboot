package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.services.GameService;
import com.ipi.gestionchampionnat.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private GameService gameService;

    @GetMapping("/team/{id}")
    public String showTeamDetails(@PathVariable Long id, Model model) {
        Team team = teamService.findById(id).get();
        List<Game> teamGames = gameService.getGamesByTeam(team.getId());
        model.addAttribute("team", team);
        model.addAttribute("games", teamGames);
        return "team/details";
    }
}
