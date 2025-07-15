package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.dao.UserDao;
import com.ipi.gestionchampionnat.pojos.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/connexion")
    public String formConnexion() {
        return "login";
    }

    @PostMapping("/connexion")
    public String connexion(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userDao.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("UserConnecte", user);
            return "redirect:/";
        } else {
            model.addAttribute("erreur", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/connexion";
    }
}
