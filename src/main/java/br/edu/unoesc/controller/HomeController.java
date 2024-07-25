package br.edu.unoesc.controller;


import br.edu.unoesc.models.Education;
import br.edu.unoesc.models.Person;
import br.edu.unoesc.service.EducationService;
import br.edu.unoesc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private PersonService personService;


    @GetMapping("/home")
    public String showEducationForm() {
        return "home/index";
    }


}
