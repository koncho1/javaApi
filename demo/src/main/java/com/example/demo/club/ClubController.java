package com.example.demo.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="club")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<Club> getClubs(){
        return clubService.getClubs();
    }

    @GetMapping(path="{clubId}")
    public Optional<Club> getClub(@PathVariable("clubId") Long clubId){
        return clubService.getClub(clubId);
    }

    @PostMapping(path="{clubId}")
    public void AddNewClub(@RequestBody Club club,
                            @PathVariable("clubId") Long clubId){
        clubService.AddClubWithId(clubId,club);
    }

    @PostMapping
    public void AddClub(@RequestBody Club club){
        clubService.AddClub(club);
    }

    @PutMapping(path="{clubId}")
    public void UpdateClub(
            @PathVariable("clubId") Long clubId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Long year){
        clubService.UpdateClub(clubId,name,city,year);
    }

    @PutMapping
    public void UpdateClubs(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Long year){
                clubService.UpdateClubs(city,year);
    }

    @DeleteMapping(path="{clubId}")
    public void DeleteClub(@PathVariable("clubId") Long clubId){
        clubService.deleteClub(clubId);
    }

    @DeleteMapping
    public void DeleteClubs(){
        clubService.deleteClubs();
    }

    @RequestMapping(path="query")
    public Page<Club> query(){
        return clubService.query();
    }
}
