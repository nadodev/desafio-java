package br.edu.unoesc.controllers;

import br.edu.unoesc.entities.User;
import br.edu.unoesc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (userService.userExists(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        userService.save(user);
        return "redirect:/login";
    }
}
