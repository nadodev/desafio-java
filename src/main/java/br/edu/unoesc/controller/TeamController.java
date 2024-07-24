package br.edu.unoesc.controller;

import br.edu.unoesc.service.TeamService;
import br.edu.unoesc.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public String listTeams(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "teams.index";
    }

    @GetMapping("/{id}")
    public String viewTeam(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        return "team";
    }

    @PostMapping
    public String createTeam(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/teams";
    }

    // Additional methods for updating and deleting teams
}
