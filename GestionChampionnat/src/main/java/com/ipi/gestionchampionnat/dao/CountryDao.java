package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
}