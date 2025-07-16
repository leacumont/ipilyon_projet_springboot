package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.ChampionshipDao;
import com.ipi.gestionchampionnat.dao.CountryDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;
    @Autowired
    private ChampionshipDao championshipDao;

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryDao.findById(id).get();
    }

    @Override
    public Country save(Country country) {
        return countryDao.save(country);
    }

    @Override
    public void delete(Long id) {
        countryDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        countryDao.deleteAll();
    }
}
