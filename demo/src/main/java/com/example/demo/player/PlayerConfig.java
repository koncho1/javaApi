package com.example.demo.player;

import com.example.demo.manager.Manager;
import com.example.demo.manager.ManagerSave;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.List;

@Configuration
public class PlayerConfig {

    @Bean
    CommandLineRunner clp(PlayerSave playerSave){
        return args -> {
            Player man=new Player(1L,"Malang","Sarr",24L,1L);
            Player ars=new Player(2L,"Kai","Havertz",24L,1L);
            man.setModificationDate(ZonedDateTime.now());
            ars.setModificationDate(ZonedDateTime.now());
            playerSave.saveAll(List.of(man,ars));
        };
    }
}
