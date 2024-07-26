package br.edu.unoesc.controller;

import br.edu.unoesc.models.Person;
import br.edu.unoesc.service.FactService;
import br.edu.unoesc.service.TeamService;
import br.edu.unoesc.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private FactService factService;

    @GetMapping("/list")
    public String listTeams(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "teams/list";
    }
    @GetMapping("/create")
    public String index(Model model) {
        model.addAttribute("team", new Team());
        return "teams/index";
    }
    @GetMapping("/mural/{teamId}")
    public String listTeams(@PathVariable("teamId") Long teamId, Model model) {
        List<Person> teamMembers = teamService.findMembersByTeamId(teamId);
        System.out.println("Team members: " + teamMembers);

        Map<Person, String> memberFacts = new HashMap<>();
        for (Person member : teamMembers) {
            memberFacts.put(member, factService.getRandomFact());
        }

        model.addAttribute("teamMembers", memberFacts);
        Team team = teamService.findById(teamId);
        model.addAttribute("team", team);

        return "teams/list_teams";
    }


    @GetMapping("/{id}")
    public String viewTeam(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        return "team";
    }

    @PostMapping()
    public String createTeam(@ModelAttribute Team team) {

        System.out.println("Team: " + team);
       teamService.save(team);
       return "redirect:/teams/mural/" + team.getId();
    }

}
