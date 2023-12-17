package com.example.demo.player;

import com.example.demo.club.Club;
import com.example.demo.club.ClubSave;
import com.example.demo.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerSave playerSave;

    @Autowired
    private ClubSave clubSave;

    @Autowired
    public PlayerService(PlayerSave playerSave){this.playerSave=playerSave;}
    public List<Player> GetPlayers() {
        return playerSave.findAll();
    }

    public Optional<Player> GetPlayer(Long id){
        return playerSave.findById(id);
    }

    public void AddPlayer(Player player) {
        if (clubSave.findById(player.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(player.getClubId());
            player.setClub(club.get());
            Long topId = playerSave.getTopId();
            player.setId(topId + 1L);
            player.setModificationDate(ZonedDateTime.now());
            playerSave.save(player);
        }

    }

    public void AddPlayerById(Long playerId, Player player) {
        player.setId(playerId);
        if (playerSave.existsById(playerId)) {
            throw new IllegalStateException("Player with this id already exists");
        }
        if (clubSave.findById(player.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(player.getClubId());
            player.setClub(club.get());
            player.setModificationDate(ZonedDateTime.now());
            playerSave.save(player);
        }
    }

    public void UpdatePlayer(Long playerId, Long age, String name, String surname) {
        if(!playerSave.existsById(playerId)){
            throw new IllegalStateException("Player with this id doesn't exist");
        }
        Optional<Player> playerOptional=playerSave.findById(playerId);
        Player player=playerOptional.get();
        if(name!=null && name.length()>0){
            player.setName(name);
        }
        if(surname!=null && surname.length()>0){
            player.setSurname(surname);
        }
        if(age!=null && age>0){
            player.setAge(age);
        }
        player.setModificationDate(ZonedDateTime.now());
        playerSave.save(player);
    }

    public void UpdatePlayers(Long age, String name, String surname) {
        List<Player> players=playerSave.findAll();
        for(Player player:players){
            if(name!=null && name.length()>0){
                player.setName(name);
            }
            if(surname!=null && surname.length()>0){
                player.setSurname(surname);
            }
            if(age!=null && age>0){
                player.setAge(age);
            }
            player.setModificationDate(ZonedDateTime.now());
            playerSave.save(player);
        }
    }

    public void DeletePlayers() {
        playerSave.deleteAll();
    }

    public void DeletePlayer(Long playerId){
        if(!playerSave.findById(playerId).isPresent()){
            throw new IllegalStateException("Player with this id doesn't exist");
        }
        else{
            Optional<Player> optionalPlayer=playerSave.findById(playerId);
            Player player=optionalPlayer.get();
            playerSave.delete(player);
        }
    }
}
