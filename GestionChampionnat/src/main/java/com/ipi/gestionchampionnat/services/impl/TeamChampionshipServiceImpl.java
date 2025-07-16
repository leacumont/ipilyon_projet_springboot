package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.StadiumDao;
import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;
import com.ipi.gestionchampionnat.services.TeamChampionshipService;
import com.ipi.gestionchampionnat.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamChampionshipServiceImpl implements TeamChampionshipService {
    @Autowired
    TeamChampionshipDao teamChampionshipDao;

    @Override
    public List<TeamChampionship> findAll() {
        return teamChampionshipDao.findAll();
    }

    @Override
    public Optional<TeamChampionship> findById(Long id) {
        return teamChampionshipDao.findById(id);
    }

    @Override
    public TeamChampionship save(TeamChampionship teamChampionship) {
        return teamChampionshipDao.save(teamChampionship);
    }

    @Override
    public void delete(Long id) {
        teamChampionshipDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        teamChampionshipDao.deleteAll();
    }
}
