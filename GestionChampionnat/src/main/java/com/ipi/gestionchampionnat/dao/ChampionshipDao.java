package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipDao extends JpaRepository<Championship, Long> {
    List<Championship> findByCountry(Country country);

    @Query("SELECT c FROM Championship c WHERE c.startDate <= CURRENT_DATE AND c.endDate >= CURRENT_DATE")
    List<Championship> findActiveChampionships();

    @Query("SELECT c FROM Championship c JOIN c.teamChampionships tc WHERE tc.team.id = :teamId")
    List<Championship> findChampionshipsByTeamId(@Param("teamId") int teamId);
}