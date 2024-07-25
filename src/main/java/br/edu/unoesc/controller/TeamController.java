package br.edu.unoesc.controller;

import br.edu.unoesc.models.Person;
import br.edu.unoesc.service.TeamService;
import br.edu.unoesc.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;


    @GetMapping("/create")
    public String index(Model model) {
        model.addAttribute("team", new Team());
        return "teams/index";
    }
    @GetMapping("/mural/{teamId}")
    public String listTeams(@PathVariable("teamId") Long teamId, Model model) {
        List<Person> teamMembers = teamService.findMembersByTeamId(teamId);
        System.out.println("Team members: " + teamMembers);

        RestTemplate restTemplate = new RestTemplate();
        String factUrl = "https://uselessfacts.jsph.pl/api/v2/facts/random";
        String randomFact = "";

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(factUrl, Map.class);
            Map<String, Object> body = response.getBody();
            if (body != null) {
                randomFact = (String) body.get("text");
            }
        } catch (Exception e) {
            randomFact = "Unable to fetch fact"; // Mensagem de fallback
            e.printStackTrace();
        }

        model.addAttribute("teamMembers", teamMembers);
        model.addAttribute("randomFact", randomFact);
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
