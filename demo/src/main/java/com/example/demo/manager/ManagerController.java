package com.example.demo.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService){this.managerService=managerService;}

    @GetMapping
    public List<Manager> getManagers(){
        return managerService.getManagers();
    }

    @GetMapping(path="{manId}")
    public Optional<Manager> manager(@PathVariable("manId") Long manId){
        return managerService.getManager(manId);
    }

    @PostMapping
    public void AddManager(@RequestBody Manager manager){
        managerService.AddManager(manager);
    }

    @PostMapping(path="{manId}")
    public void AddManagerById(@PathVariable("manId") Long manId,
                                @RequestBody Manager manager){
        managerService.AddManagerById(manId,manager);
    }

    @PutMapping(path="{manId}")
    public void UpdateManager(@PathVariable("manId") Long manId,
                                @RequestParam(required = false) Long age,
                                @RequestParam(required = false)String name,
                              @RequestParam(required = false)String surname){
        managerService.UpdateManager(manId,age,name,surname);
    }

    @PutMapping
    public void UpdateManagers(@RequestParam(required = false) Long age,
                               @RequestParam(required = false)String name,
                               @RequestParam(required = false)String surname){
        managerService.UpdateManagers(age,name,surname);
    }

    @DeleteMapping
    public void DeleteManagers(){
        managerService.DeleteManagers();
    }

    @DeleteMapping(path="{manId}")
    public void DeleteManager(@PathVariable("manId") Long manId){
        managerService.DeleteManager(manId);
    }
}
