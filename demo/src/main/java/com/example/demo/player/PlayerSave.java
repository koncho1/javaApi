package com.example.demo.player;

import com.example.demo.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerSave extends JpaRepository<Player,Long> {

    @Query(value = "SELECT MAX(id) FROM player", nativeQuery = true)
    Long getTopId();

    @Query("SELECT p FROM Player p WHERE p.clubId=?1")
    Optional<Player> findPlayerByClubId(Long clubId);
}
