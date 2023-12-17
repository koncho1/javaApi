package com.example.demo.club;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.List;

@Configuration
public class ClubConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClubSave clubSave){
        return args -> {
           Club chelsea= new Club(1L,"Chelsea F.C.","Londyn",1905L);
           Club arsenal= new Club(2L,"Arsenal F.C.","Londyn",1886L);
            Club legia= new Club(3L,"Legia Warszwa","Warszwa",1916L);

           chelsea.setModificationDate(ZonedDateTime.now());
           arsenal.setModificationDate(ZonedDateTime.now());
           legia.setModificationDate(ZonedDateTime.now());
           clubSave.saveAll(List.of(chelsea,arsenal,legia));
        };

    }
}
