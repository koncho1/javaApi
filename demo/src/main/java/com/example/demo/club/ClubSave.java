package com.example.demo.club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ClubSave  extends JpaRepository<Club,Long> {

    @Query("SELECT c FROM Club c WHERE c.name=?1")
    Optional<Club> findClubByName(String name);
}
