package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Stadium;

import java.util.List;

public interface StadiumService {
    List<Stadium> findAll();
    Stadium findById(Long id);
    Stadium save(Stadium stadium);
    void delete(Long id);
    void deleteAll();
    List<Stadium> getStadiumsByCapacityRange(int minCapacity, int maxCapacity);
    List<Stadium> getStadiumsWithMaxCapacity();
}
