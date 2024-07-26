package br.edu.unoesc.controllers;


import br.edu.unoesc.services.EducationService;
import br.edu.unoesc.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
