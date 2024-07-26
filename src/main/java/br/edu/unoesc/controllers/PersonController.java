package br.edu.unoesc.controllers;


import br.edu.unoesc.entities.Person;
import br.edu.unoesc.entities.Team;
import br.edu.unoesc.enums.Gender;
import br.edu.unoesc.exceptions.DuplicateResourceException;
import br.edu.unoesc.services.PersonService;
import br.edu.unoesc.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private TeamService teamService;


    @GetMapping("/persons/list")
    public String list(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons/list";
    }
    @GetMapping("/persons/create")
    public String index(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("person", new Person());
        model.addAttribute("genders", Arrays.asList(Gender.values()));
        model.addAttribute("teams", teams);
        return "persons/index";
    }

    @PostMapping("/persons/save")
    public String savePerson(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "persons/index";
        }
        try {

            personService.save(person);
            return "redirect:/persons/list";

        } catch (DuplicateResourceException e) {
            List<Team> teams = teamService.findAll();
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("person", person);
            model.addAttribute("teams", teams);
            model.addAttribute("genders", Arrays.asList(Gender.values()));

            return "persons/index";
        }
    }

    @GetMapping("/persons/details/{teamId}")
    public String details(@PathVariable("teamId") Long teamId, Model model) {
        model.addAttribute("person", personService.findById(teamId));
        return "persons/person_details";
    }

    @PostMapping("/persons/delete/{id}")
    public String deleteTeam(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        Team team = personService.findById(id).getTeam();
        try {
            personService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Team deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting team.");
        }
        return "redirect:/persons/list";
    }
}
