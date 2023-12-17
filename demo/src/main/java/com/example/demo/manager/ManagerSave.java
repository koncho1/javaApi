package com.example.demo.manager;

import com.example.demo.stadium.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerSave extends JpaRepository<Manager,Long> {

    @Query(value = "SELECT MAX(id) FROM manager", nativeQuery = true)
    Long getTopId();

    @Query("SELECT m FROM Manager m WHERE m.clubId=?1")
    Optional<Manager> findManagerByClubId(Long clubId);

}
