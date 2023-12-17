package com.example.demo.club;

import com.example.demo.manager.Manager;
import com.example.demo.manager.ManagerSave;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerSave;
import com.example.demo.stadium.Stadium;
import com.example.demo.stadium.StadiumSave;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.internal.util.ZonedDateTimeComparator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.club.strona.page;


@Service
public class ClubService {

    private final ClubSave clubSave;

    @Autowired
    private StadiumSave stadiumSave;

    @Autowired
    private strona Strona;

    @Autowired
    private PlayerSave playerSave;

    @Autowired
    private ManagerSave managerSave;

    @Autowired
    public ClubService(ClubSave clubSave){
        this.clubSave=clubSave;
    }

    public List<Club> getClubs(){
        return clubSave.findAll();
    }

    public Optional<Club> getClub(Long clubId){
        return clubSave.findById(clubId);
    }


    public void AddClub(Club club) {
        Optional<Club> exists=clubSave.findClubByName(club.getName());
        Long topId=clubSave.getTopId();
        club.setId(topId+1L);
        if(clubSave.existsById(club.getId()) || exists.isPresent()){
            throw new IllegalStateException("Club with this name or id already exists");
        }
        club.setModificationDate(ZonedDateTime.now());
        clubSave.save(club);
    }

    public void AddClubWithId(Long clubId, Club club) {
        club.setId(clubId);
        Optional<Club> exists=clubSave.findClubByName(club.getName());
        if(clubSave.existsById(club.getId()) || exists.isPresent()){
            throw new IllegalStateException("Club with this name or id already exists");
        }
        club.setModificationDate(ZonedDateTime.now());
        clubSave.save(club);

    }

    public void deleteClub(Long clubId) {
        boolean exists= clubSave.existsById(clubId);
        if(!exists){
            throw new IllegalStateException("Club with this id doesn't exist");
        }
        else{
            Optional<Stadium> stadiumOptional=stadiumSave.findStadiumByClubId(clubId);
            Stadium stadium=stadiumOptional.get();
            stadiumSave.delete(stadium);
            Optional<Manager> managerOptional=managerSave.findManagerByClubId(clubId);
            Manager manager=managerOptional.get();
            managerSave.delete(manager);
            List<Player> players=playerSave.findAll();
            for(Player player:players){
                playerSave.delete(player);
            }

            clubSave.deleteById(clubId);
        }


    }

    public void deleteClubs(){
        List<Stadium> stadiums=stadiumSave.findAll();
        for(Stadium stadium:stadiums){
            stadiumSave.delete(stadium);
        }
        List<Manager> managers=managerSave.findAll();
        for(Manager manager:managers){
            managerSave.delete(manager);
        }
        List<Player> players=playerSave.findAll();
        for(Player player:players){
            playerSave.delete(player);
        }
        clubSave.deleteAll();
    }

    @Transactional
    public void UpdateClub(Long clubId, String name, String city, Long year) {
        boolean exists= clubSave.existsById(clubId);
        if(!exists){
            throw new IllegalStateException("Club with this id doesn't exist");
        }
        else{
            Club club=clubSave.findById(clubId).orElseThrow();
            if(name!=null && name.length()>0){
                club.setName(name);
            }
            if(city!=null && city.length()>0){
                club.setCity(city);
            }
            if(year!=null && year>1843){
                club.setYear(year);
            }
            club.setModificationDate(ZonedDateTime.now());
        }
    }

    @Transactional
    public void UpdateClubs(String city,Long year){
        List<Club> clubs=clubSave.findAll();
        for(Club club: clubs){
            if(city!=null && city.length()>0){
                club.setCity(city);
            }
            if(year!=null && year>1843){
                club.setYear(year);
            }
            club.setModificationDate(ZonedDateTime.now());
        }

    }

    public Page<Club> query(){
        Page<Club> clubPage=Strona.findAll(page);
        return clubPage;

    }
}
