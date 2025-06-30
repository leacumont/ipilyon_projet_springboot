package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Day;

import java.util.List;

public interface DayService {
    List<Day> findAll();
    Day findById(Long id);
    Day save(Day day);
    void delete(Long id);
    void deleteAll();
}
