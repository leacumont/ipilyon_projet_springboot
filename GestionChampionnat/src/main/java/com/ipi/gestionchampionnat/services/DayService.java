package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Day;

import java.util.List;
import java.util.Optional;

public interface DayService {
    List<Day> findAll();
    Day findById(Long id);
    Day save(Day day);
    void delete(Long id);
    void deleteAll();
    Optional<Day> getDayByChampionshipAndNumber(Long championshipId, String number);
}
