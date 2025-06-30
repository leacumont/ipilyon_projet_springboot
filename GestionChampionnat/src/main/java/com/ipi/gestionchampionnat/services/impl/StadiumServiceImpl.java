package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.StadiumDao;
import com.ipi.gestionchampionnat.pojos.Stadium;
import com.ipi.gestionchampionnat.services.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService {
    @Autowired
    StadiumDao stadiumDao;

    @Override
    public List<Stadium> findAll() {
        return stadiumDao.findAll();
    }

    @Override
    public Stadium findById(Long id) {
        return stadiumDao.findById(id).get();
    }

    @Override
    public Stadium save(Stadium stadium) {
        return stadiumDao.save(stadium);
    }

    @Override
    public void delete(Long id) {
        stadiumDao.delete(stadiumDao.findById(id).get());
    }

    @Override
    public void deleteAll() {
        stadiumDao.deleteAll();
    }
}
