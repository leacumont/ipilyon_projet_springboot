package com.ipi.gestionchampionnat.controller;


import com.ipi.gestionchampionnat.dao.UserDao;
import com.ipi.gestionchampionnat.pojos.Role;
import com.ipi.gestionchampionnat.pojos.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class GestionChampionnatController {
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GestionChampionnatController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/connexion")
    public String formConnexion() {
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connexion(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userDao.findByEmail(email); // <-- Optional
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("UserConnecte", user);
            return "redirect:/";
        } else {
            model.addAttribute("erreur", true);
            return "connexion";
        }
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin/dashboard")
    public String backoffice(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("/inscription")
    public String showRegisterForm() {
        return "inscription"; // Ton HTML
    }

    @PostMapping("/inscription")
    public String processRegister(@RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  Model model) {
        if (userDao.findByEmail(email) != null) {
            model.addAttribute("error", "Cet email est déjà utilisé");
            return "inscription";
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setCreationDate(LocalDate.now());

        userDao.save(user);
        return "redirect:/connexion";
    }
}
