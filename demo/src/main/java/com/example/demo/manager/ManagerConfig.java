package com.example.demo.manager;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.List;

@Configuration
public class ManagerConfig {
    @Bean
    CommandLineRunner clm(ManagerSave managerSave){
        return args -> {
            Manager man=new Manager(1L,41L,"Mikel","Arteta",2L);
            Manager ars=new Manager(2L,51L,"Mauricio","Pochettino",1L);
            man.setModificationDate(ZonedDateTime.now());
            ars.setModificationDate(ZonedDateTime.now());
            managerSave.saveAll(List.of(man,ars));
        };
    }


}
