package br.edu.unoesc.controller;


import br.edu.unoesc.models.Administrator;
import br.edu.unoesc.repositorio.AdministradoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AdministradoresRepository adminRepo;

    @GetMapping("/entrar")
    public String index(Model model) {
        model.addAttribute("titulo", "Login");
        return "login/index";
    }


    @PostMapping("/logar")
    public String logar(Model model, Administrator adminParams) {
        System.out.println(adminParams.getEmail());
        System.out.println(adminParams.getPassword());

        Administrator admin = this.adminRepo.Login(adminParams.getEmail(), adminParams.getPassword());

        if (admin != null) {
            return "redirect:/administrators";
        } else {
            model.addAttribute("mensagem", "Usuário ou senha inválidos");
            return "login/index";
        }

    }
}
