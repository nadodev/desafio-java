package br.edu.unoesc.controllers;


import br.edu.unoesc.entities.Education;
import br.edu.unoesc.entities.Person;
import br.edu.unoesc.services.EducationService;
import br.edu.unoesc.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/educations")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private PersonService personService;


    @GetMapping("/form/{personId}")
    public String showEducationForm(@PathVariable Long personId, Model model) {
        model.addAttribute("personId", personId);
        return "education_form";
    }


    @PostMapping("/associate/{personId}")
    public String associateEducationWithPerson(@PathVariable Long personId, Education education) {

        Person person = personService.findById(personId);

        if (person != null) {
            education.setPerson(person);
            educationService.save(education);
            return "redirect:/persons/details/" + person.getId();
        } else {
            throw new RuntimeException("Person not found");
        }
    }


    @GetMapping("/{id}")
    public String getPersonById(@PathVariable Long id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "person_details";
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteById(id);
    }
}
