package br.edu.unoesc.controllers;


import br.edu.unoesc.services.EducationService;
import br.edu.unoesc.services.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public String showEducationForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName());

        model.addAttribute("isLoggedIn", isLoggedIn);
        return "home/index";
    }
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

}
