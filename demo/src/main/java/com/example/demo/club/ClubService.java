package com.example.demo.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ClubService {

    private final ClubSave clubSave;

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
        if(clubSave.existsById(club.getId()) || exists.isPresent()){
            throw new IllegalStateException("Club with this name or id already exists");
        }
        clubSave.save(club);
    }

    public void deleteClub(Long clubId) {
        boolean exists= clubSave.existsById(clubId);
        if(!exists){
            throw new IllegalStateException("Club with this id doesn't exist");
        }
        else{
            clubSave.deleteById(clubId);
        }

    }

    public void deleteClubs(){
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
        }
    }
}
