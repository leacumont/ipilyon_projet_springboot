package com.ipi.gestionchampionnat.controller;


import com.ipi.gestionchampionnat.dao.UserDao;
import com.ipi.gestionchampionnat.pojos.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GestionChampionnatController {

    private final UserDao userDao;

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
        User user = userDao.findByEmailAndPassword(email, password);
        if (user != null) {
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
}
