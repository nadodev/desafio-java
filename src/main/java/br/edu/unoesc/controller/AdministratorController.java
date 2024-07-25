package br.edu.unoesc.controller;


import br.edu.unoesc.models.Administrator;
import br.edu.unoesc.repositorio.AdministradoresRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    private AdministradoresRepository administratorResponsitory;

    @GetMapping("/administrators")
    public String index(Model model, @CookieValue(value = "admin", defaultValue = "") String adminEmail) {

        if (adminEmail.isEmpty()) {
            return "redirect:/entrar";
        }

        List<Administrator> admins =  (List<Administrator>) administratorResponsitory.findAll();
        model.addAttribute("administrators",admins);
        return "administrator/index";
    }
}
