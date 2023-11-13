package com.example.VulServer.AppUser.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepo extends MongoRepository<AppUser, String> {
    List<AppUser> findAll();
}
