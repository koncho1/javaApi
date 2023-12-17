package com.example.demo.stadium;

import com.example.demo.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StadiumSave extends JpaRepository<Stadium,Long> {

    @Query("SELECT s FROM Stadium s WHERE s.name=?1")
    Optional<Stadium> findStadiumByName(String name);

    @Query(value = "SELECT MAX(id) FROM stadium", nativeQuery = true)
    Long getTopId();

    @Query("SELECT s FROM Stadium s WHERE s.clubId=?1")
    Optional<Stadium> findStadiumByClubId(Long clubId);
}
