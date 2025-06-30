package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.Game;

import java.util.List;

public interface GameService {
    List<Game> findAll();
    Game findById(Long id);
    Game save(Game game);
    void delete(Long id);
    void deleteAll();
}
