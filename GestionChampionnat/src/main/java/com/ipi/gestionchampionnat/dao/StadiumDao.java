package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumDao extends JpaRepository<Stadium, Long> {
    List<Stadium> findByCapacityGreaterThan(int capacity);

    List<Stadium> findByCapacityBetween(int minCapacity, int maxCapacity);

    @Query("SELECT s FROM Stadium s WHERE s.capacity = (SELECT MAX(st.capacity) FROM Stadium st)")
    List<Stadium> findStadiumsWithMaxCapacity();
}