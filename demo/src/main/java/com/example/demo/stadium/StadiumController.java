package com.example.demo.stadium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService){this.stadiumService=stadiumService;}

    @GetMapping
    public List<Stadium> getStadiums(){return stadiumService.getStadiums();}

    @GetMapping(path="{stadiumId}")
    public Optional<Stadium> getStadium(@PathVariable ("stadiumId") Long stadiumId){return  stadiumService.getStadium(stadiumId);}


    @PostMapping(path="{stadiumId}")
    public void AddNewStadium(@RequestBody Stadium stadium,
                              @PathVariable ("stadiumId") Long stadiumId){ stadiumService.AddStadiumWithId(stadiumId,stadium);}

    @PostMapping
    public void AddStadium(@RequestBody Stadium stadium){ stadiumService.AddStadium(stadium);

    }

    @PutMapping
    public void UpdateStadiums(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long seats

    ){
        stadiumService.UpdateStadiums(name,seats);
    }

    @PutMapping(path="{stadiumId}")
    public void UpdateStadium(
            @PathVariable ("stadiumId") Long stadiumId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long seats,
            @RequestParam(required = false) Long clubId
    ){
        stadiumService.UpdateStadium(stadiumId,name,seats,clubId);
    }

    @DeleteMapping(path="{stadiumId}")
    public void DeleteStadium(@PathVariable("stadiumId") Long stadiumId){
        stadiumService.DeleteStadium(stadiumId);
    }

    @DeleteMapping
    public void DeleteStadiums(){
        stadiumService.DeleteStadiums();
    }


}
