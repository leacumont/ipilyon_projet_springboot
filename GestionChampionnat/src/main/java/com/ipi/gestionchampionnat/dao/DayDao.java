package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayDao extends JpaRepository<Day, Long> {
    List<Day> findByChampionship_Id(int championshipId);

    List<Day> findByChampionship_IdOrderByNumberAsc(int championshipId);

    Optional<Day> findByChampionship_IdAndNumber(int championshipId, String number);

    @Query("SELECT d FROM Day d WHERE d.championship.id = :championshipId AND d.number = :number")
    Optional<Day> findDayByChampionshipAndNumber(@Param("championshipId") Long championshipId, @Param("number") String number);
}