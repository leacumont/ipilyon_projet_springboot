package com.ipi.gestionchampionnat.controller;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class InitDataController {

    //@Autowired
    public InitDataController(){}

    @PostConstruct
    private void init() {}
}
