package br.edu.unoesc.controllers;

import br.edu.unoesc.entities.Person;
import br.edu.unoesc.services.FactService;
import br.edu.unoesc.services.TeamService;
import br.edu.unoesc.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "teams/create";
    }
    @GetMapping("/mural/{teamId}")
    public String mural(@PathVariable("teamId") Long teamId, Model model) {
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

       teamService.save(team);
       return "redirect:/teams/mural/" + team.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            teamService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Team deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting team.");
        }
        return "redirect:/teams/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "teams/edit";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id") Long id, @ModelAttribute Team team, RedirectAttributes redirectAttributes) {
        Team existingTeam = teamService.findById(id);
        if (existingTeam != null) {
            existingTeam.setName(team.getName());
            existingTeam.setDepartment(team.getDepartment());
            teamService.save(existingTeam);
            redirectAttributes.addFlashAttribute("message", "Team updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error updating team.");
        }
        return "redirect:/teams/list";
    }

}
