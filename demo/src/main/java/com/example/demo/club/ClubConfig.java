package com.example.demo.club;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClubConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClubSave clubSave){
        return args -> {
           Club chelsea= new Club(1L,"Chelsea","Londyn",1990L);
           Club arsenal= new Club(2L,"Arsenal","Londyn",1899L);

           clubSave.saveAll(List.of(chelsea,arsenal));
        };

    }
}
