package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.ChampionshipDao;
import com.ipi.gestionchampionnat.dao.DayDao;
import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.services.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {
    @Autowired
    ChampionshipDao championshipDao;

    @Autowired
    DayDao dayDao;

    @Override
    public List<Championship> findAll() {
        return championshipDao.findAll();
    }

    @Override
    public Championship findById(Long id) {
        return championshipDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Championnat introuvable avec id : " + id));
    }

    @Override
    public Championship save(Championship championship) {
        return championshipDao.save(championship);
    }

    @Override
    public void delete(Long id) {
        championshipDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        championshipDao.deleteAll();
    }

    @Override
    public List<Championship> getChampionshipsByCountry(Country country) {
        return championshipDao.findByCountry(country);
    }

    @Override
    public Long getLastDayId(Long championshipId) {
        List<Day> days = dayDao.findByChampionshipOrderByDayDateDesc(championshipId);
        if (days.isEmpty()) {
            return null; // ou throw exception selon ta logique
        }
        return days.get(0).getId();  // la journée la plus récente
    }
}
