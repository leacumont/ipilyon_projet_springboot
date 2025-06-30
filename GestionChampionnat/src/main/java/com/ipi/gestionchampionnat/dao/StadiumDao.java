package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumDao extends JpaRepository<Stadium, Long> {
}