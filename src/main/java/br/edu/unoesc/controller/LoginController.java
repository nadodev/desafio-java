package br.edu.unoesc.controller;


import br.edu.unoesc.repositorio.AdministradoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private AdministradoresRepository adminRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
