package com.example.demo.manager;

import com.example.demo.club.Club;
import com.example.demo.club.ClubSave;
import com.example.demo.stadium.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerSave managerSave;

    @Autowired
    private ClubSave clubSave;


    @Autowired
    public ManagerService(ManagerSave managerSave) {
        this.managerSave = managerSave;
    }

    public List<Manager> getManagers() {
        return managerSave.findAll();
    }

    public Optional<Manager> getManager(Long manId) {
        return managerSave.findById(manId);
    }

    public void AddManager(Manager manager) {
        if (clubSave.findById(manager.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(manager.getClubId());
            manager.setClub(club.get());
            Long topId = managerSave.getTopId();
            manager.setId(topId + 1L);
            manager.setModificationDate(ZonedDateTime.now());
            managerSave.save(manager);
        }

    }

    public void AddManagerById(Long manId, Manager manager) {
        manager.setId(manId);
        if (managerSave.existsById(manId)) {
            throw new IllegalStateException("Manager with this id already exists");
        }
        if (clubSave.findById(manager.getClubId()).isPresent()) {
            Optional<Club> club = clubSave.findById(manager.getClubId());
            manager.setClub(club.get());
            manager.setModificationDate(ZonedDateTime.now());
            managerSave.save(manager);
        }
    }

    public void UpdateManager(Long manId, Long age, String name, String surname) {
        if(!managerSave.existsById(manId)){
            throw new IllegalStateException("Manager with this id doesn't exist");
        }
        Optional<Manager> managerOptional=managerSave.findById(manId);
        Manager manager=managerOptional.get();
        if(name!=null && name.length()>0){
            manager.setName(name);
        }
        if(surname!=null && surname.length()>0){
            manager.setSurname(surname);
        }
        if(age!=null && age>0){
            manager.setAge(age);
        }
        manager.setModificationDate(ZonedDateTime.now());
        managerSave.save(manager);
    }

    public void UpdateManagers(Long age, String name, String surname) {
        List<Manager> managers=managerSave.findAll();
        for(Manager manager:managers){
            if(name!=null && name.length()>0){
                manager.setName(name);
            }
            if(surname!=null && surname.length()>0){
                manager.setSurname(surname);
            }
            if(age!=null && age>0){
                manager.setAge(age);
            }
            manager.setModificationDate(ZonedDateTime.now());
            managerSave.save(manager);
        }
    }

    public void DeleteManagers() {
        managerSave.deleteAll();
    }

    public void DeleteManager(Long manId){
        if(!managerSave.findById(manId).isPresent()){
            throw new IllegalStateException("Manager with this id doesn't exist");
        }
        else{
            Optional<Manager> optionalManager=managerSave.findById(manId);
            Manager manager=optionalManager.get();
            managerSave.delete(manager);
        }
    }
}
