package com.ipi.gestionchampionnat.services.impl;

import com.ipi.gestionchampionnat.dao.DayDao;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayServiceImpl implements DayService {

    @Autowired
    DayDao dayDao;

    @Override
    public List<Day> findAll() {
        return dayDao.findAll();
    }

    @Override
    public Day findById(Long id) {
        return dayDao.findById(id).get();
    }

    @Override
    public Day save(Day day) {
        return dayDao.save(day);
    }

    @Override
    public void delete(Long id) {
        dayDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dayDao.deleteAll();
    }
}
