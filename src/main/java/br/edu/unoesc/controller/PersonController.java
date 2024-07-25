package br.edu.unoesc.controller;


import br.edu.unoesc.person.Person;
import br.edu.unoesc.service.PersonService;
import br.edu.unoesc.service.TeamService;
import br.edu.unoesc.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private TeamService teamService;


    @GetMapping("/create")
    public String index(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("teams", teamService.findAll());
        return "persons/index";
    }

    @PostMapping()
    public String create(Person person) {
        personService.save(person);
        return "redirect:/persons";
    }
}
