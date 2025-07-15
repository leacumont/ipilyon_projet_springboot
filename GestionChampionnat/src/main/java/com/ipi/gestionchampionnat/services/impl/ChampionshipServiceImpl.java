package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.ChampionshipDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.services.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {

    @Autowired
    ChampionshipDao championshipDao;

    @Override
    public List<Championship> findAll() {
        return championshipDao.findAll();
    }

    @Override
    public Championship findById(Long id) {
        return championshipDao.findById(id).get();
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
    public List<Championship> getChampionshipsByTeam(Long teamId) {
        return championshipDao.findChampionshipsByTeamId(teamId);
    }
}
