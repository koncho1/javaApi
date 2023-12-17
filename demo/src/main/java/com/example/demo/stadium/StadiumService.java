package com.example.demo.stadium;


import com.example.demo.club.Club;
import com.example.demo.club.ClubSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {
    private final StadiumSave stadiumSave;

    @Autowired
    private ClubSave clubSave;

    @Autowired
    public StadiumService(StadiumSave stadiumSave){this.stadiumSave=stadiumSave;}

    public List<Stadium> getStadiums(){return stadiumSave.findAll();}

    public Optional<Stadium> getStadium(Long stadiumId){ return stadiumSave.findById(stadiumId);
    }

    public void AddStadium(Stadium stadium){
        if (clubSave.findById(stadium.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(stadium.getClubId());
            stadium.setClub(club.get());
            Long topId = stadiumSave.getTopId();
            stadium.setId(topId + 1L);
            stadium.setModificationDate(ZonedDateTime.now());
            stadiumSave.save(stadium);
        }

    }

    public void AddStadiumWithId(Long stadiumId,Stadium stadium){
        stadium.setId(stadiumId);
        if (stadiumSave.existsById(stadiumId)) {
            throw new IllegalStateException("Stadium with this id already exists");
        }
        if (clubSave.findById(stadium.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(stadium.getClubId());
            stadium.setClub(club.get());
            stadium.setModificationDate(ZonedDateTime.now());
            stadiumSave.save(stadium);
        }
    }


    public void UpdateStadiums(String name, Long seats) {
        List<Stadium> stadiums=stadiumSave.findAll();
        for(Stadium stadium:stadiums){
            if(name!=null && name.length()>0){
                stadium.setName(name);
            }
            if(seats!=null && seats>0){
                stadium.setSeats(seats);
            }
            stadium.setModificationDate(ZonedDateTime.now());
            stadiumSave.save(stadium);
        }

    }

    public void UpdateStadium(Long id, String name, Long seats, Long clubId) {
        if(!stadiumSave.existsById(id)){
            throw new IllegalStateException("Stadium with this id doesn't exist");
        }
        Optional<Stadium> stadiumOptional=stadiumSave.findById(id);
        Stadium stadium=stadiumOptional.get();
        if(name!=null && name.length()>0){
            stadium.setName(name);
        }
        if(seats!=null && seats>0){
            stadium.setSeats(seats);
        }
        stadium.setClubId(clubId);
        stadium.setModificationDate(ZonedDateTime.now());
        stadiumSave.save(stadium);

    }

    public void DeleteStadiums(){
        stadiumSave.deleteAll();
    }

    public void DeleteStadium(Long stadiumId){
        boolean exists=stadiumSave.existsById(stadiumId);
        if(!exists){
            throw new IllegalStateException("Stadium with this id doesn't exists");
        }
        else{
            stadiumSave.deleteById(stadiumId);
        }
    }
}
