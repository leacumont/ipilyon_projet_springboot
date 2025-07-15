package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamChampionshipDao extends JpaRepository<TeamChampionship, Long> {
    List<TeamChampionship> findByChampionship_Id(Long championshipId);

    List<TeamChampionship> findByTeam_Id(Long teamId);

    Optional<TeamChampionship> findByChampionship_IdAndTeam_Id(Long championshipId, Long teamId);

    @Query("SELECT tc FROM TeamChampionship tc WHERE tc.championship.id = :championshipId")
    List<TeamChampionship> findTeamChampionshipsByChampionshipId(@Param("championshipId") Long championshipId);
}
