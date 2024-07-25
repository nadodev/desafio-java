package br.edu.unoesc.controller;


import br.edu.unoesc.models.Administrator;
import br.edu.unoesc.repositorio.AdministradoresRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AdministradoresRepository adminRepo;

    @GetMapping("/login")
    public String login() {
        return "login/index";
    }
}
