package br.edu.unoesc.controllers;


import br.edu.unoesc.entities.Person;
import br.edu.unoesc.services.PersonService;
import br.edu.unoesc.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("person", new Person());
        model.addAttribute("teams", teamService.findAll());
        return "persons/index";
    }

    @PostMapping("/create")
    public String create(Person person) {
        personService.save(person);
        return  "redirect:/teams/mural/" + person.getTeam().getId();
    }

    @GetMapping("/persons/details/{teamId}")
    public String details(@PathVariable("teamId") Long teamId, Model model) {
        model.addAttribute("person", personService.findById(teamId));
        return "persons/person_details";
    }
}
