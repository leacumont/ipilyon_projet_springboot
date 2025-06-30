package com.ipi.gestionchampionnat.services;

import com.ipi.gestionchampionnat.pojos.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);
    void deleteAll();
}