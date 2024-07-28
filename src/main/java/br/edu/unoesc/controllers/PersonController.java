package br.edu.unoesc.controllers;


import br.edu.unoesc.entities.Education;
import br.edu.unoesc.entities.Person;
import br.edu.unoesc.entities.Team;
import br.edu.unoesc.enums.Gender;
import br.edu.unoesc.exceptions.DuplicateResourceException;
import br.edu.unoesc.services.EducationService;
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

    @Autowired
    private EducationService educationService;



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
           redirectAttributes.addFlashAttribute("error",  "Error deleting team.");
        }
        return "redirect:/persons/list";
    }

    @GetMapping("/persons/list")
    public String listPerson(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons/list";
    }

    @GetMapping("/persons/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model) {
        Person person = personService.findById(id);
        List<Team> teams = teamService.findAll();
        Education education = educationService.findByPersonId(id);
        model.addAttribute("person", person);
        model.addAttribute("teams", teams);
        model.addAttribute("educations", education);

        return "persons/edit";
    }


    @PostMapping("/persons/update/{id}")
    public String updatePerson(
            @PathVariable("id") Long personId,
            @ModelAttribute @Valid Person updatedPerson,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("person", updatedPerson);
            model.addAttribute("teams", teamService.findAll());
            model.addAttribute("genders", Arrays.asList(Gender.values()));
            model.addAttribute("educations", educationService.findByPersonId(personId));
            return "persons/edit";
        }

        Person existingPerson = personService.findById(personId);

        if (existingPerson == null) {
            redirectAttributes.addFlashAttribute("error", "Person not found.");
            return "redirect:/persons/list";
        }

        try {
            boolean existingCpf = personService.isCpfInUseByAnotherPerson(updatedPerson.getCpf(), updatedPerson.getId());
            boolean existingEmail = personService.isEmailInUseByAnotherPerson(updatedPerson.getEmail(), updatedPerson.getId());

            if (existingCpf) {
                throw new DuplicateResourceException("CPF já existe: " + updatedPerson.getCpf());
            }

            if (existingEmail) {
                throw new DuplicateResourceException("Email já existe: " + updatedPerson.getEmail());
            }

            existingPerson.setName(updatedPerson.getName());
            existingPerson.setCpf(updatedPerson.getCpf());
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setPhone(updatedPerson.getPhone());
            existingPerson.setBirth_date(updatedPerson.getBirth_date());
            existingPerson.setTeam(updatedPerson.getTeam());

            existingPerson.getEducation().clear();
            existingPerson.getEducation().addAll(updatedPerson.getEducation());

            personService.save(existingPerson);
            redirectAttributes.addFlashAttribute("message", "Person updated successfully!");
            return "redirect:/persons/list";
        } catch (DuplicateResourceException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("person", updatedPerson);
            model.addAttribute("teams", teamService.findAll());
            model.addAttribute("genders", Arrays.asList(Gender.values()));
            model.addAttribute("educations", educationService.findByPersonId(personId));
            return "persons/edit";
        }
    }



    @PostMapping("/persons/removeEducation/{id}")
    public String deleteEducation(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        Education education = educationService.findById(id);
        Person person = personService.findById(education.getPerson().getId());
        try {
            educationService.deleteById(education.getId());
            redirectAttributes.addFlashAttribute("message", "Team deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting team.");
        }
        return "redirect:/persons/edit/" + person.getId();
    }
}
