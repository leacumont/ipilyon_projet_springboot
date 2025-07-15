package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
    List<Team> findByCountry_Id(Long countryId);

    List<Team> findByStadium_Id(Long stadiumId);

    @Query("SELECT t FROM Team t JOIN t.teamChampionships tc WHERE tc.championship.id = :championshipId")
    List<Team> findTeamsByChampionshipId(@Param("championshipId") Long championshipId);
}