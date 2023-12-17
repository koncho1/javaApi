package com.example.demo.player;


import com.example.demo.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){this.playerService=playerService;}

    @GetMapping
    public List<Player> getPlayers(){
        return playerService.GetPlayers();
    }

    @GetMapping(path = "{playerId}")
    public Optional<Player> getPlayer(@PathVariable("playerId") Long playerId){
        return playerService.GetPlayer(playerId);
    }

    @PostMapping
    public void AddPlayer(@RequestBody Player player){
        playerService.AddPlayer(player);
    }

    @PostMapping(path="{playerId}")
    public void AddPlayerById(@PathVariable("playerId") Long playerId,
                               @RequestBody Player player){
        playerService.AddPlayerById(playerId,player);
    }

    @PutMapping(path="{playerId}")
    public void UpdatePlayer(@PathVariable("playerId") Long playerId,
                              @RequestParam(required = false) Long age,
                              @RequestParam(required = false)String name,
                              @RequestParam(required = false)String surname){
        playerService.UpdatePlayer(playerId,age,name,surname);
    }

    @PutMapping
    public void UpdatePlayers(@RequestParam(required = false) Long age,
                               @RequestParam(required = false)String name,
                               @RequestParam(required = false)String surname){
        playerService.UpdatePlayers(age,name,surname);
    }

    @DeleteMapping
    public void DeletePlayers(){
        playerService.DeletePlayers();
    }

    @DeleteMapping(path="{playerId}")
    public void DeletePlayer(@PathVariable("playerId") Long playerId){
        playerService.DeletePlayer(playerId);
    }
}
